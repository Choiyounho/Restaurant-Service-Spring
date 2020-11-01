package com.soten.eatgo.restaurant.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTests {

    @Test
    void creation() {
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "");
        assertThat(restaurant.getId()).isEqualTo(1004L);
        assertThat(restaurant.getName()).isEqualTo("Bob zip");
        assertThat(restaurant.getAddress()).isEqualTo("");
    }

    @Test
    void information() {
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        assertThat(restaurant.getInformation()).isEqualTo("Bob zip in Seoul");
    }

}