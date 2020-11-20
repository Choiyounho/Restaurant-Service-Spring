package com.soten.eatgo.review.domain;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableJpaRepositories
public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findAllByRestaurantId(long restaurantId);

    List<Review> findAll();

}
