package com.webfinances.category;

import com.webfinances.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public List<Category> findCategoriesByCategoryTypeId(Long id) {
        return categoryRepo.findCategoriesByCategoryTypeId(id);
    }

    public Category findCategoryById(Long id) {
        return categoryRepo.findCategoryById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + id + " was not found!"));
    }
}
