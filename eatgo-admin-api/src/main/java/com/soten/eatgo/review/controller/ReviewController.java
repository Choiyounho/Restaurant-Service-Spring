package com.soten.eatgo.review.controller;

import com.soten.eatgo.review.domain.Review;
import com.soten.eatgo.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.soten.eatgo.global.CommonConstant.URL_RESTAURANTS;
import static com.soten.eatgo.global.CommonConstant.URL_REVIEWS;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<Review> list() {
        return reviewService.getReviews();
    }

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(@PathVariable("restaurantId") Long restaurantId,
                                    @Valid @RequestBody Review resource) throws URISyntaxException {
        Review review = reviewService.addReview(restaurantId, resource);
        String url = URL_RESTAURANTS + restaurantId + URL_REVIEWS + review.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
