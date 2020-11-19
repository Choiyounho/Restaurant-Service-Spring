package com.soten.eatgo.category.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @Test
    @DisplayName("카테고리 생성")
    void creation() {
        Category category = Category.builder().name("Korean Food").build();

        assertThat(category.getName()).isEqualTo("Korean Food");
    }

}