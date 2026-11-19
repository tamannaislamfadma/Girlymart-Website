package com.girlymart.girlymart.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customize_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomizeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @Column(length = 1000)
    private String description;

    private String whatsappNumber;

    private LocalDateTime submittedAt;
}