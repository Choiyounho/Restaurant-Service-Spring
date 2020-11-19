package com.soten.eatgo.restaurant.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findById(Long id);

    List<Restaurant> findAll();

    List<Restaurant> findAllByAddressContaining(String region);
}
