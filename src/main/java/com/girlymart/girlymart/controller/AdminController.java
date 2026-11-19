package com.girlymart.girlymart.controller;

import com.girlymart.girlymart.model.*;
import com.girlymart.girlymart.repository.*;
import com.girlymart.girlymart.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final FileStorageService fileStorageService;
    private final CustomizeRequestRepository customizeRequestRepository;
    private final BannerRepository bannerRepository;
    private final UserService userService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("productCount", productService.getAll().size());
        model.addAttribute("reviewCount", reviewService.getAllReviews().size());
        model.addAttribute("customizeCount", customizeRequestRepository.count());
        model.addAttribute("userCount", userService.findAll().size());
        return "admin/dashboard";
    }

    // ---- PRODUCTS ----
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("newProduct", new Product());
        return "admin/products";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam(required = false) MultipartFile image) throws Exception {
        if (image != null && !image.isEmpty()) {
            product.setImageUrl(fileStorageService.save(image));
        }
        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    // ---- REVIEWS ----
    @GetMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "admin/reviews";
    }

    @GetMapping("/reviews/approve/{id}")
    public String approveReview(@PathVariable Long id) {
        reviewService.approve(id);
        return "redirect:/admin/reviews";
    }

    @GetMapping("/reviews/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return "redirect:/admin/reviews";
    }

    // ---- CUSTOMIZE REQUESTS ----
    @GetMapping("/customize-requests")
    public String customizeRequests(Model model) {
        model.addAttribute("requests", customizeRequestRepository.findAllByOrderBySubmittedAtDesc());
        return "admin/customize-requests";
    }

    // ---- BANNERS ----
    @GetMapping("/banners")
    public String banners(Model model) {
        model.addAttribute("banners", bannerRepository.findAll());
        model.addAttribute("newBanner", new Banner());
        return "admin/banners";
    }

    @PostMapping("/banners/save")
    public String saveBanner(@ModelAttribute Banner banner,
                             @RequestParam(required = false) MultipartFile image) throws Exception {
        if (image != null && !image.isEmpty()) {
            banner.setImageUrl(fileStorageService.save(image));
        }
        bannerRepository.save(banner);
        return "redirect:/admin/banners";
    }

    @GetMapping("/banners/delete/{id}")
    public String deleteBanner(@PathVariable Long id) {
        bannerRepository.deleteById(id);
        return "redirect:/admin/banners";
    }

    // ---- INSIGHTS ----
    @GetMapping("/insights")
    public String insights(Model model) {
        model.addAttribute("totalProducts", productService.getAll().size());
        model.addAttribute("totalReviews", reviewService.getAllReviews().size());
        model.addAttribute("totalUsers", userService.findAll().size());
        model.addAttribute("totalCustomize", customizeRequestRepository.count());
        return "admin/insights";
    }
}