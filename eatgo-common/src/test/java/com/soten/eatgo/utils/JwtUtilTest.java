package com.soten.eatgo.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private String secret = "12345678901234567890123456789012";
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(secret);
    }

    @Test
    @DisplayName("jwt 토큰 생성")
    void createToken() {
        String token = jwtUtil.createToken(1004L, "John", null);

        assertThat(token).contains(".");
    }

    @Test
    @DisplayName("JwtUtil에서 claims 얻기")
    void getClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";
        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("name")).isEqualTo("John");
        assertThat(claims.get("userId", Long.class)).isEqualTo(1004L);
    }

}
