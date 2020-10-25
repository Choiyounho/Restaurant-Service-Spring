package com.soten.eatgo.controller;

import com.soten.eatgo.application.RestaurantService;
import com.soten.eatgo.domain.MenuItem;
import com.soten.eatgo.domain.MenuItemRepository;
import com.soten.eatgo.domain.Restaurant;
import com.soten.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    @Autowired
//    private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }

}
