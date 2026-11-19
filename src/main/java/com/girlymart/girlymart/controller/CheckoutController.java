package com.girlymart.girlymart.controller;

import com.girlymart.girlymart.model.Order;
import com.girlymart.girlymart.model.Product;
import com.girlymart.girlymart.model.User;
import com.girlymart.girlymart.repository.OrderRepository;
import com.girlymart.girlymart.repository.UserRepository;
import com.girlymart.girlymart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final ProductService productService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @PostMapping("/checkout/{productId}")
    public String checkoutPage(@PathVariable Long productId,
                               @RequestParam(required = false) String selectedSize,
                               @AuthenticationPrincipal UserDetails userDetails,
                               Model model) {
        if (userDetails == null) {
            return "redirect:/login"; // Force login to buy
        }

        Product product = productService.findById(productId).orElseThrow();
        User user = userRepository.findByPhone(userDetails.getUsername()).orElseThrow();

        model.addAttribute("product", product);
        model.addAttribute("user", user);
        model.addAttribute("selectedSize", selectedSize);
        return "checkout";
    }

    @PostMapping("/checkout/confirm")
    public String confirmOrder(@RequestParam Long productId,
                               @RequestParam String customerName,
                               @RequestParam String customerPhone,
                               @RequestParam String customerAddress,
                               @RequestParam String deliveryArea,
                               @RequestParam String paymentMethod,
                               @RequestParam(required = false) String selectedSize,
                               @AuthenticationPrincipal UserDetails userDetails) {

        Product product = productService.findById(productId).orElseThrow();
        User user = userRepository.findByPhone(userDetails.getUsername()).orElseThrow();

        // Save updated user details if they edited them during checkout
        user.setName(customerName);
        user.setDeliveryPhone(customerPhone);
        user.setAddress(customerAddress);
        userRepository.save(user);

        // Calculate Totals
        Double deliveryCharge = deliveryArea.equals("Inside Dhaka") ? 80.0 : 130.0;
        Double totalAmount = product.getPrice() + deliveryCharge;

        // Save Order
        Order order = Order.builder()
                .user(user)
                .product(product)
                .selectedSize(selectedSize)
                .customerName(customerName)
                .customerPhone(customerPhone)
                .customerAddress(customerAddress)
                .deliveryArea(deliveryArea)
                .deliveryCharge(deliveryCharge)
                .totalAmount(totalAmount)
                .paymentMethod(paymentMethod)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        orderRepository.save(order);

        return "redirect:/?orderSuccess=true"; // Sends them back home with a success message
    }
}