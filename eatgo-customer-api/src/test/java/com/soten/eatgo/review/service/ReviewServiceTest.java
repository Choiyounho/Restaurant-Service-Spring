package com.soten.eatgo.review.service;

import com.soten.eatgo.review.domain.Review;
import com.soten.eatgo.review.domain.ReviewRepository;
import com.soten.eatgo.review.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    @DisplayName("식당 리뷰 추가")
    void addReview() {
        Review review = Review.builder()
                .name("YH")
                .score(3)
                .description("Tasty good")
                .build();

        reviewService.addReview(1004L, review);

        verify(reviewRepository).save(any());
    }

}
