package com.girlymart.girlymart.controller;

import com.girlymart.girlymart.model.CustomizeRequest;
import com.girlymart.girlymart.repository.CustomizeRequestRepository;
import com.girlymart.girlymart.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CustomizeController {

    private final CustomizeRequestRepository customizeRequestRepository;
    private final FileStorageService fileStorageService;

    @GetMapping("/customize")
    public String customizePage() {
        return "customize";
    }

    @PostMapping("/customize/submit")
    public String submit(@RequestParam(required = false) MultipartFile image,
                         @RequestParam String description,
                         @RequestParam String whatsappNumber) throws Exception {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = fileStorageService.save(image);
        }
        CustomizeRequest req = CustomizeRequest.builder()
                .imageUrl(imageUrl)
                .description(description)
                .whatsappNumber(whatsappNumber)
                .submittedAt(LocalDateTime.now())
                .build();
        customizeRequestRepository.save(req);
        return "redirect:/customize?submitted";
    }
}