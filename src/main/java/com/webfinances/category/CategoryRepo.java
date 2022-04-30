package com.webfinances.category;

import com.webfinances.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Transactional
    void deleteCategoryById(Long id);

    Optional<Category> findCategoryById(Long id);

    List<Category> findCategoriesByCategoryTypeId(Long id);
}
