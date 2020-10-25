package com.soten.eatgo.controller;

import com.soten.eatgo.application.RestaurantService;
import com.soten.eatgo.domain.MenuItemRepository;
import com.soten.eatgo.domain.MenuItemRepositoryImplement;
import com.soten.eatgo.domain.RestaurantRepository;
import com.soten.eatgo.domain.RestaurantRepositoryImplement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @SpyBean(RestaurantRepositoryImplement.class)
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImplement.class)
    private MenuItemRepository menuItemRepository;

    @SpyBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @Test
    @DisplayName("list Test")
    void list() throws Exception {
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Bob zip\"")));

    }

    @Test
    void detail() throws Exception {
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":1004")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Bob zip\"")))
                .andExpect(content().
                        string(containsString("Kimchi")));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":2020")))
                .andExpect(content()
                        .string(containsString("\"name\":\"Cyber Food\"")));
    }

}
