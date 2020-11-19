package com.soten.eatgo.region.service;

import com.soten.eatgo.region.domain.Region;
import com.soten.eatgo.region.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class RegionServiceTest {

    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        regionService = new RegionService(regionRepository);
    }

    @Test
    @DisplayName("region 확인")
    void getRegions() {
        List<Region> MockRegions = new ArrayList<>();
        MockRegions.add(Region.builder().name("Seoul").build());

        given(regionRepository.findAll()).willReturn(MockRegions);

        List<Region> regions = regionService.getRegions();

        Region region = regions.get(0);

        assertThat(region.getName()).isEqualTo("Seoul");
    }

    @Test
    @DisplayName("region 추가")
    void addRegion() {
        Region region = regionService.addRegion("Seoul");

        verify(regionRepository).save(any());

        assertThat(region.getName()).isEqualTo("Seoul");
    }

}