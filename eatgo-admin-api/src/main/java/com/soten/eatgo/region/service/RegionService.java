package com.soten.eatgo.region.service;

import com.soten.eatgo.region.domain.Region;
import com.soten.eatgo.region.domain.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();

        regions.add(Region.builder()
                          .name("Seoul")
                          .build());

        return regions;
    }

    public Region addRegion(String name) {
        Region region = Region.builder()
                              .name(name)
                              .build();

        regionRepository.save(region);

        return region;
    }

}
