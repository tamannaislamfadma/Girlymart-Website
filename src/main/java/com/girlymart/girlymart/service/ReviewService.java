package com.girlymart.girlymart.service;

import com.girlymart.girlymart.model.Review;
import com.girlymart.girlymart.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getApprovedReviews() {
        return reviewRepository.findByApprovedTrueOrderByCreatedAtDesc();
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAllByOrderByCreatedAtDesc();
    }

    public void save(Review review) {
        reviewRepository.save(review);
    }

    public void approve(Long id) {
        reviewRepository.findById(id).ifPresent(r -> {
            r.setApproved(true);
            reviewRepository.save(r);
        });
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}