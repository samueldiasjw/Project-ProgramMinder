package com.school.mindera.programminder.service;

import com.school.mindera.programminder.persistence.entity.CategoryThreadsEntity;
import com.school.mindera.programminder.persistence.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryThreadsEntity> getAllCategorys() {
        // Get all users from database
        Iterable<CategoryThreadsEntity> categoryList = categoryRepository.findAll();
        LOGGER.info("Getting All Catergorys - {}", categoryList);



        // Return list of CarDetailsDto
        return (List<CategoryThreadsEntity>) categoryList;
    }
}
