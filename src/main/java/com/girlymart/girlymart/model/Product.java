package com.girlymart.girlymart.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double price;

    private String category;

    private String imageUrl;

    private Integer stock;

    // NEW FIELD: Used for Crochet Bag size or Cat Outfit weight
    private String sizeInfo;
}