package com.webfinances.subcategory;

import com.webfinances.categorytype.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {

    Optional<Subcategory> findSubcategoryById(Long id);

    Optional<Subcategory> findSubcategoryByCategoryId(Long id);

    List<Subcategory> findSubcategoriesByCategoryId(Long id);
}
