package com.girlymart.girlymart.controller;

import com.girlymart.girlymart.model.Banner;
import com.girlymart.girlymart.repository.BannerRepository;
import com.girlymart.girlymart.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final BannerRepository bannerRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Banner> heroBanners = bannerRepository.findByTypeOrderBySortOrderAsc("HERO");
        List<Banner> adBanners = bannerRepository.findByTypeOrderBySortOrderAsc("AD");
        model.addAttribute("heroBanners", heroBanners);
        model.addAttribute("adBanners", adBanners);
        model.addAttribute("products", productService.getAll());
        model.addAttribute("reviews", reviewService.getApprovedReviews());
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("products", productService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "search";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerDTO", new com.girlymart.girlymart.dto.RegisterDTO());
        return "register";
    }
}