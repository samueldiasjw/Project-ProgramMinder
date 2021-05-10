package com.school.mindera.programminder.controller;

import com.school.mindera.programminder.persistence.entity.CategoryThreadsEntity;
import com.school.mindera.programminder.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorys")
public class CategoryController {
    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

    private final CategoryService categoryService;

    /**
     * Constructor
     * @param categoryService
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     *  Get all threads categorys
     * @return List of Categorys
     */
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<CategoryThreadsEntity>> getCategoryList() {
        List<CategoryThreadsEntity> categorys = categoryService.getAllCategorys();

        LOGGER.info("Get all Categorys - {}", categorys);
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }
}
