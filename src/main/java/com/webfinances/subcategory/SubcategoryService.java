package com.webfinances.subcategory;

import com.webfinances.category.Category;
import com.webfinances.category.CategoryRepo;
import com.webfinances.category.CategoryService;
import com.webfinances.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    private final SubcategoryRepo subcategoryRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public SubcategoryService(CategoryRepo categoryRepo, SubcategoryRepo subcategoryRepo) {
        this.categoryRepo = categoryRepo;
        this.subcategoryRepo = subcategoryRepo;
    }

    public List<Subcategory> findAllSubcategories() {
        return subcategoryRepo.findAll();
    }

    public List<Subcategory> findIncomeSubcategories() {
        List<Subcategory> subcategories = new ArrayList<>();
        List<Category> incomeCategories = categoryRepo.findCategoriesByCategoryTypeId(1L);
        for(Category category : incomeCategories) {
            Optional<Subcategory> subcategory = subcategoryRepo.findSubcategoryByCategoryId(category.getId());
            subcategories.add(subcategory.get());
        }
        return subcategories;
    }

    public List<Subcategory> findExpenseSubcategories() {
        List<Subcategory> subcategories = new ArrayList<>();
        List<Category> expenseCategories = categoryRepo.findCategoriesByCategoryTypeId(2L);
        for(Category category : expenseCategories) {
            Optional<Subcategory> subcategory = subcategoryRepo.findSubcategoryByCategoryId(category.getId());
            subcategories.add(subcategory.get());
        }
        return subcategories;
    }

    public List<Subcategory> findSubcategoriesByCategoryId(Long id) {
        return subcategoryRepo.findSubcategoriesByCategoryId(id);
    }

    public Subcategory findSubcategoryById(Long id) {
        return subcategoryRepo.findSubcategoryById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subcategory with ID " + id + " was not found!"));
    }
}
