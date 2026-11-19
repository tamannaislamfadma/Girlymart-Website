package com.girlymart.girlymart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private String address;

    private String deliveryPhone;
}