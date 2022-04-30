package com.webfinances.categorytype;

import com.webfinances.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTypeService {

    private final CategoryTypeRepo categoryTypeRepo;

    @Autowired
    public CategoryTypeService(CategoryTypeRepo categoryTypeRepo) {
        this.categoryTypeRepo = categoryTypeRepo;
    }

    public List<CategoryType> findAllCategoryTypes() {
        return categoryTypeRepo.findAll();
    }

    public CategoryType findCategoryTypeById(Long id) {
        return categoryTypeRepo.findCategoryTypeById(id)
                .orElseThrow(() -> new EntityNotFoundException("CategoryType with ID " + id + " was not found!"));
    }
}
