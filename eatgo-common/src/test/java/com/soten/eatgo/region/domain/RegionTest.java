package com.soten.eatgo.region.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegionTest {

    @Test
    @DisplayName("region 필터링")
    void creation() {
        Region region = Region.builder()
                              .name("seoul")
                              .build();

        assertThat(region.getName()).isEqualTo("seoul");
    }

}