package com.soten.eatgo.category.controller;

import com.soten.eatgo.category.domain.Category;
import com.soten.eatgo.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> list() {
        return categoryService.getCategories();
    }

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {
        String name = resource.getName();

        Category category = categoryService.addCategory(name);

        String url = "/categories/" + category.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }

}

