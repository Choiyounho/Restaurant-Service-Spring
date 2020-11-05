package com.soten.eatgo.restaurant.controller;

import com.soten.eatgo.menu.domain.MenuItem;
import com.soten.eatgo.restaurant.domain.Restaurant;
import com.soten.eatgo.restaurant.service.RestaurantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    @DisplayName("list Test")
    void list() throws Exception {

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Cow Marketplace")
                .address("Guri")
                .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Cow Marketplace\"")));

    }

    @Test
    void detail() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Cow Marketplace")
                .address("Guri")
                .build();

        Restaurant restaurant1 = Restaurant.builder()
                .id(2020L)
                .name("Omogari Kimchi")
                .address("Guri")
                .build();
        restaurant.setMenuItems(Arrays.asList(MenuItem.builder()
                .name("Kimchi")
                .build()));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant1);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Cow Marketplace\"")))
                .andExpect(content().
                        string(containsString("Kimchi")));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":2020")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Omogari Kimchi\"")));
    }

    @Test
    void createWithValidData() throws Exception {

        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}\n"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());

    }

    @Test
    void createWithInvalidData() throws Exception {

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void updateWithValidData() throws Exception {

        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"YH Bar\",\"address\":\"Guri\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "YH Bar", "Guri");
    }

    @Test
    void updateWithoutName() throws Exception {

        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"Busan\"}"))
                .andExpect(status().isBadRequest());

    }

}

