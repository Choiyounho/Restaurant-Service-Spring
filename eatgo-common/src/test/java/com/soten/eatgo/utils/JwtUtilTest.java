package com.soten.eatgo.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    @Test
    @DisplayName("jwt 토큰 생성")
    void createToken() {
        String secret = "12345678901234567890123456789012";

        JwtUtil jwtUtil = new JwtUtil(secret);

        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token).contains(".");
    }

}
