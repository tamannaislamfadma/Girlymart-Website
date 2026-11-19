package com.girlymart.girlymart.controller;

import com.girlymart.girlymart.model.User;
import com.girlymart.girlymart.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    private User getUser(UserDetails userDetails) {
        return userService.findByPhone(userDetails.getUsername());
    }

    @GetMapping
    public String cart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = getUser(userDetails);
        model.addAttribute("cartItems", cartService.getCartItems(user));
        model.addAttribute("total", cartService.getTotal(user));
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String add(@PathVariable Long productId,
                      @AuthenticationPrincipal UserDetails userDetails,
                      @RequestHeader(value = "Referer", defaultValue = "/") String referer) {
        User user = getUser(userDetails);
        cartService.addToCart(user, productId);
        return "redirect:" + referer;
    }

    @PostMapping("/remove/{itemId}")
    public String remove(@PathVariable Long itemId) {
        cartService.removeFromCart(itemId);
        return "redirect:/cart";
    }
}