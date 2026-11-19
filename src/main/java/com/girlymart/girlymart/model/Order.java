package com.girlymart.girlymart.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders") // Renamed from 'order' because 'order' is a reserved SQL word
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String selectedSize;

    // Delivery Details
    private String customerName;
    private String customerPhone;
    private String customerAddress;

    private String deliveryArea; // "Inside Dhaka" or "Outside Dhaka"
    private Double deliveryCharge;
    private Double totalAmount;
    private String paymentMethod; // "COD" or "BKASH"

    private String status; // "PENDING", "CONFIRMED", "DELIVERED"
    private LocalDateTime createdAt;
}