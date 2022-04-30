package com.webfinances.categorytype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category-types")
public class CategoryTypeController {

    private final CategoryTypeService categoryTypeService;

    @Autowired
    public CategoryTypeController(CategoryTypeService categoryTypeService) {
        this.categoryTypeService = categoryTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryType>> getAllCategoryTypes() {
        List<CategoryType> categoryTypes = categoryTypeService.findAllCategoryTypes();
        return new ResponseEntity<>(categoryTypes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CategoryType> getCategoryTypeById(@PathVariable("id") Long id) {
        CategoryType categoryType = categoryTypeService.findCategoryTypeById(id);
        return new ResponseEntity<>(categoryType, HttpStatus.OK);
    }
}
