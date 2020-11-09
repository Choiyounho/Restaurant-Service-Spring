package com.soten.eatgo.restaurant.service;

import com.soten.eatgo.menu.domain.MenuItem;
import com.soten.eatgo.menu.domain.MenuItemRepository;
import com.soten.eatgo.restaurant.domain.Restaurant;
import com.soten.eatgo.global.exception.RestaurantNotFoundException;
import com.soten.eatgo.restaurant.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTest {

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    private Restaurant newInstanceOfRestaurant() {
        return Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = newInstanceOfRestaurant();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);

    }

    @Test
    @DisplayName("식당 정보 불러오기")
    void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId()).isEqualTo(1004L);
    }

    @Test
    @DisplayName("식당 정보 불러오기 성공")
    void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId()).isEqualTo(1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName()).isEqualTo("Kimchi");
    }

    @Test
    @DisplayName("식당 정보 불러오기 실패 시 404에러 메세지 출력")
    void getRestaurantWithNotExisted() {
        assertThatExceptionOfType(RestaurantNotFoundException.class)
                .isThrownBy(() -> restaurantService.getRestaurant(404L))
                .withMessage("Could not find restaurant 404");
    }

    @Test
    @DisplayName("식당 추가")
    void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1004L);
            return restaurant;
        });

        Restaurant restaurant = newInstanceOfRestaurant();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId()).isEqualTo(1004L);
    }

    @Test
    @DisplayName("식당 정보 수정")
    public void updateRestaurant() {
        Restaurant restaurant = newInstanceOfRestaurant();

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L, "Beer house", "Busan");

        assertThat(restaurant.getName()).isEqualTo("Beer house");
        assertThat(restaurant.getAddress()).isEqualTo("Busan");
    }

}