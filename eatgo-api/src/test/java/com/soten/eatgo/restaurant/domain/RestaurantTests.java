package com.soten.eatgo.restaurant.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTests {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
    }

    @Test
    @DisplayName("/restaurants : 식당 등록 성공")
    void creation() {
        assertThat(restaurant.getId()).isEqualTo(1004L);
        assertThat(restaurant.getName()).isEqualTo("Bob zip");
        assertThat(restaurant.getAddress()).isEqualTo("Seoul");
    }

    @Test
    @DisplayName("/restaurants : 식당 정보 불러오기")
    void information() {
        assertThat(restaurant.getInformation()).isEqualTo("Bob zip in Seoul");
    }

}