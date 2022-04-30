package com.webfinances.subcategory;

import com.webfinances.category.Category;
import com.webfinances.category.CategoryRepo;
import com.webfinances.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryService {

    private final SubcategoryRepo subcategoryRepo;

    @Autowired
    public SubcategoryService(SubcategoryRepo subcategoryRepo) {
        this.subcategoryRepo = subcategoryRepo;
    }

    public List<Subcategory> findAllSubcategories() {
        return subcategoryRepo.findAll();
    }

    public List<Subcategory> findSubcategoriesByCategoryId(Long id) {
        return subcategoryRepo.findSubcategoryByCategoryId(id);
    }

    public Subcategory findSubcategoryById(Long id) {
        return subcategoryRepo.findSubcategoryById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subcategory with ID " + id + " was not found!"));
    }
}
