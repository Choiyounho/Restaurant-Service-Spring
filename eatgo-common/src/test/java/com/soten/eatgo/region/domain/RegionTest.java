package com.soten.eatgo.region.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegionTest {

    @Test
    void creation() {
        Region region = Region.builder().name("seoul").build();

        assertThat(region.getName()).isEqualTo("seoul");
    }

}