package com.soten.eatgo.user.domain;

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

}