package com.revanya.apps.services.review.service;


import com.revanya.apps.services.review.entities.Review;
import com.revanya.apps.services.review.dto.ReviewDTO;
import com.revanya.apps.services.review.mappers.ReviewMapper;
import com.revanya.apps.services.review.service.repositories.ReviewRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReviewService {

    @Inject
    ReviewRepository reviewRepository;


    public ReviewDTO getReview(Long id) {
        Review review = reviewRepository.findById(id);
        if (review == null) {
            throw new IllegalStateException("Review not found for id: " + id);
        }
        return ReviewMapper.toDTO(review);
    }

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.listAll();
        return reviews.stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = ReviewMapper.toEntity(reviewDTO);
        reviewRepository.persist(review);
        return ReviewMapper.toDTO(review);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id);
        if (review != null) {
            reviewRepository.delete(review);
        } else {
            throw new IllegalStateException("Review not found for id: " + id);
        }
    }

    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review existingReview = reviewRepository.findById(id);
        if (existingReview == null) {
            throw new IllegalStateException("Review not found for id: " + id);
        }
        Review reviewToUpdate = ReviewMapper.toEntity(reviewDTO);
        reviewToUpdate.setId(id); // Ensure ID is retained
        reviewRepository.updateReview(reviewToUpdate);
        return ReviewMapper.toDTO(reviewToUpdate);
    }

    // ... Add other specific methods for Review as required ...

}

