package com.girlymart.girlymart.controller;

import com.girlymart.girlymart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // NEW MAPPING: Shows the single product profile page
    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow());
        return "product-details";
    }

    @GetMapping("/bangles")
    public String bangles(Model model) {
        model.addAttribute("products", productService.getByCategory("Bangles"));
        model.addAttribute("pageTitle", "Bangles");
        return "category";
    }

    @GetMapping("/crochet/bag")
    public String crochetBag(Model model) {
        model.addAttribute("products", productService.getByCategory("Crochet Bag"));
        model.addAttribute("pageTitle", "Crochet Bag");
        return "category";
    }

    @GetMapping("/crochet/cat-outfit")
    public String crochetCat(Model model) {
        model.addAttribute("products", productService.getByCategory("Crochet Cat Outfit"));
        model.addAttribute("pageTitle", "Crochet Cat Outfit");
        return "category";
    }

    @GetMapping("/crochet/keyring")
    public String crochetKeyring(Model model) {
        model.addAttribute("products", productService.getByCategory("Crochet Keyring"));
        model.addAttribute("pageTitle", "Crochet Keyring");
        return "category";
    }

    @GetMapping("/crochet/others")
    public String crochetOthers(Model model) {
        model.addAttribute("products", productService.getByCategory("Crochet Others"));
        model.addAttribute("pageTitle", "Crochet Others");
        return "category";
    }

    @GetMapping("/bowclip")
    public String bowclip(Model model) {
        model.addAttribute("products", productService.getByCategory("Bowclip"));
        model.addAttribute("pageTitle", "Bowclip");
        return "category";
    }
}