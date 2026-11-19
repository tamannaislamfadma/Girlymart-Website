package com.girlymart.girlymart.service;

import com.girlymart.girlymart.model.*;
import com.girlymart.girlymart.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public void addToCart(User user, Long productId) {
        var existing = cartItemRepository.findByUserAndProductId(user, productId);
        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + 1);
            cartItemRepository.save(item);
        } else {
            Product product = productRepository.findById(productId).orElseThrow();
            CartItem item = CartItem.builder()
                    .user(user)
                    .product(product)
                    .quantity(1)
                    .build();
            cartItemRepository.save(item);
        }
    }

    public void removeFromCart(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }

    public double getTotal(User user) {
        return cartItemRepository.findByUser(user).stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
    }
}