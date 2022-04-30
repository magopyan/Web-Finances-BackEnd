package com.webfinances.categorytype;

import com.webfinances.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CategoryTypeRepo extends JpaRepository<CategoryType, Long> {

    @Transactional
    void deleteCategoryTypeById(Long id);

    Optional<CategoryType> findCategoryTypeById(Long id);
}
