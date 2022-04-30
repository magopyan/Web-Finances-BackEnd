package com.webfinances.subcategory;

import com.webfinances.categorytype.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {

    @Transactional
    void deleteSubcategoryById(Long id);

    Optional<Subcategory> findSubcategoryById(Long id);

    List<Subcategory> findSubcategoryByCategoryId(Long id);
}
