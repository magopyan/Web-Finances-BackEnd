package com.webfinances.subcategory;

import com.webfinances.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subcategory>> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryService.findAllSubcategories();
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }

    @GetMapping("/all/income")
    public ResponseEntity<List<Subcategory>> getIncomeSubcategories() {
        List<Subcategory> subcategories = subcategoryService.findIncomeSubcategories();
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }

    @GetMapping("/all/expense")
    public ResponseEntity<List<Subcategory>> getExpenseSubcategories() {
        List<Subcategory> subcategories = subcategoryService.findExpenseSubcategories();
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }

    @GetMapping("/by-category/{id}")
    public ResponseEntity<List<Subcategory>> getSubcategoriesByCategoryId(@PathVariable("id") Long id) {
        List<Subcategory> subcategories = subcategoryService.findSubcategoriesByCategoryId(id);
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable("id") Long id) {
        Subcategory subcategory = subcategoryService.findSubcategoryById(id);
        return new ResponseEntity<>(subcategory, HttpStatus.OK);
    }
}
