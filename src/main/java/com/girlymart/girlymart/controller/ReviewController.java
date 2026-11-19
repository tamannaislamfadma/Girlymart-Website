package com.girlymart.girlymart.controller;

import com.girlymart.girlymart.model.Review;
import com.girlymart.girlymart.model.User;
import com.girlymart.girlymart.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final FileStorageService fileStorageService;
    private final UserService userService;

    @GetMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewService.getApprovedReviews());
        return "reviews";
    }

    @PostMapping("/reviews/submit")
    public String submit(@RequestParam(required = false) MultipartFile photo,
                         @RequestParam(required = false) String description,
                         @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        if ((photo == null || photo.isEmpty()) && (description == null || description.isBlank())) {
            return "redirect:/reviews?error=empty";
        }
        String photoUrl = null;
        if (photo != null && !photo.isEmpty()) {
            photoUrl = fileStorageService.save(photo);
        }
        User user = userDetails != null ? userService.findByPhone(userDetails.getUsername()) : null;
        Review review = Review.builder()
                .photoUrl(photoUrl)
                .description(description)
                .approved(false)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        reviewService.save(review);
        return "redirect:/reviews?submitted";
    }
}