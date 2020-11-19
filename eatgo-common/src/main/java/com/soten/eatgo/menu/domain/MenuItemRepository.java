package com.soten.eatgo.menu.domain;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableJpaRepositories
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

    List<MenuItem> findAllByRestaurantId(Long restaurantId);

    void deleteById(Long id);
}
