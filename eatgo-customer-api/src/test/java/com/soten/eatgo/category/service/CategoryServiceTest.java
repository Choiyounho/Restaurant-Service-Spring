package com.soten.eatgo.category.service;

import com.soten.eatgo.category.domain.Category;
import com.soten.eatgo.category.domain.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    @DisplayName("category 확인")
    void getCategory() {
        List<Category> MockCategories = new ArrayList<>();
        MockCategories.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(MockCategories);

        List<Category> categories = categoryService.getCategories();

        Category region = categories.get(0);

        assertThat(region.getName()).isEqualTo("Korean Food");
    }

}
