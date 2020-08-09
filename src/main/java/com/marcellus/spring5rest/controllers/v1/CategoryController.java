package com.marcellus.spring5rest.controllers.v1;

import com.marcellus.spring5rest.api.v1.model.CategoryDTO;
import com.marcellus.spring5rest.api.v1.model.CategoryListDTO;
import com.marcellus.spring5rest.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {

        return new CategoryListDTO(categoryService.getAllCategories());
    }
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryName(@PathVariable String name) {

        return categoryService.getCategoryByName(name);
    }
}
