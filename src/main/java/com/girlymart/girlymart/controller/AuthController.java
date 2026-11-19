package com.girlymart.girlymart.controller;

import com.girlymart.girlymart.dto.RegisterDTO;
import com.girlymart.girlymart.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterDTO dto, Model model) {
        try {
            userService.register(dto);
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}