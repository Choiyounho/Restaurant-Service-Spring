package com.soten.eatgo.restaurant.controller;

import com.soten.eatgo.global.exception.RestaurantNotFoundException;
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

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Cow Marketplace\"")));

    }

    @Test
    @DisplayName("/restaurants/{id} : id 식당 정보 불러오기")
    void detailWithExisted() throws Exception {
        Restaurant restaurant = newInstanceOfRestaurant(1004L, "Cow Marketplace", "Guri");

        Restaurant restaurant1 = newInstanceOfRestaurant(2020L, "Omogari Kimchi", "Guri");

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant1);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Cow Marketplace\"")));
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

    @Test
    @DisplayName("/restaurants : 식당 추가 성공")
    void createWithValidData() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            return newInstanceOfRestaurant(1234L, restaurant.getName(), restaurant.getAddress());
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
    @DisplayName("/restaurants : 식당 추가 실패")
    void createWithInvalidData() throws Exception {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("/restaurants/{id} : 식당 id의 이름과 주소 변경")
    void updateWithValidData() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"YH Bar\",\"address\":\"Guri\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "YH Bar", "Guri");
    }

    @Test
    @DisplayName("/restaurants/{id} : 잘못된 정보 입력 시 400 state 코드 발생")
    void updateWithoutName() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"Busan\"}"))
                .andExpect(status().isBadRequest());
    }

}

