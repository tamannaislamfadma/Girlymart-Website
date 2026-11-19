package com.girlymart.girlymart.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "banners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String type; // "HERO" or "AD"

    private Integer sortOrder;
}