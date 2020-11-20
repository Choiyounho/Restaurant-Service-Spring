package com.soten.eatgo.restaurant.controller;

import com.soten.eatgo.global.exception.RestaurantNotFoundException;
import com.soten.eatgo.menu.domain.MenuItem;
import com.soten.eatgo.restaurant.domain.Restaurant;
import com.soten.eatgo.restaurant.service.RestaurantService;
import com.soten.eatgo.review.domain.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    private Restaurant newInstanceOfRestaurant(Long id, String name, String address) {
        return Restaurant.builder()
                .id(id)
                .name(name)
                .address(address)
                .build();
    }

    @Test
    @DisplayName("/restaurants : 전체 식당 목록 불러오기")
    void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(newInstanceOfRestaurant(1004L, "Cow Marketplace", "Guri"));

        given(restaurantService.getRestaurants("Guri")).willReturn(restaurants);

        mvc.perform(get("/restaurants?region=Guri"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Cow Marketplace\"")));

    }

    @Test
    @DisplayName("/restaurants/{id} : id 식당 정보 상세보기")
    void detailWithExisted() throws Exception {
        Restaurant restaurant = newInstanceOfRestaurant(1004L, "Cow Marketplace", "Guri");

        Restaurant restaurant1 = newInstanceOfRestaurant(2020L, "Omogari Kimchi", "Guri");

        restaurant.setMenuItems(Arrays.asList(MenuItem.builder()
                .name("Kimchi")
                .build()));

        Review review = Review.builder()
                .name("YH")
                .score(5)
                .description("Tasty good")
                .build();
        restaurant.setReviews(Arrays.asList(review));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant1);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Cow Marketplace\"")))
                .andExpect(content().
                        string(containsString("Kimchi")))
                .andExpect(content().
                        string(containsString("Tasty good")));
    }

    @Test
    @DisplayName("/restaurants/{id} : id 식당 정보가 없을 시 예외 처리")
    public void detailWithNoExisted() throws Exception {
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));

    }

}
