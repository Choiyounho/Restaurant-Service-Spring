package com.soten.eatgo.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void creation() {
        User user = User.builder()
                .name("younho")
                .email("maxosa@naver.com")
                .level(100L)
                .build();

        assertThat(user.getName()).isEqualTo("younho");
        assertThat(user.isAdmin()).isTrue();
    }

    @Test
    @DisplayName("레스토랑 주인인지 확인")
    void restaurantOwner() {
        User user = User.builder().level(1L).build();

        assertThat(user.isRestaurantOwner()).isFalse();

        user.setRestaurantId(1004L);

        assertThat(user.isRestaurantOwner()).isTrue();
        assertThat(user.getRestaurantId()).isEqualTo(1004L);
    }

}
