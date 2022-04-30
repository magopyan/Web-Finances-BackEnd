package com.webfinances.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Transactional
    void deleteTransactionById(Long id);

    Optional<Transaction> findTransactionById(Long id);

    List<Transaction> findAllByAccountId(Long id);

    List<Transaction> findAllBySubcategoryId(Long id);
}
