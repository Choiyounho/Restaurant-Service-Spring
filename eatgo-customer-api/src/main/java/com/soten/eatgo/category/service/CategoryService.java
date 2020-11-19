package com.soten.eatgo.category.service;

import com.soten.eatgo.category.domain.Category;
import com.soten.eatgo.category.domain.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        categories.add(Category.builder().name("Seoul").build());

        return categories;
    }

}
