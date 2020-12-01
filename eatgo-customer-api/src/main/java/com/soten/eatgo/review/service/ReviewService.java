package com.soten.eatgo.review.service;

import com.soten.eatgo.review.domain.Review;
import com.soten.eatgo.review.domain.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, String name, Integer score, String description) {
        Review review = Review.builder()
                .restaurantId(restaurantId)
                .name(name)
                .score(score)
                .description(description)
                .build();
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }
}
