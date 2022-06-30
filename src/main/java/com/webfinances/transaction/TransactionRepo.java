package com.webfinances.transaction;

import com.webfinances.account.Account;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Transactional
    void deleteTransactionById(Long id);

    Optional<Transaction> findTransactionById(Long id);

    List<Transaction> findByUserIdAndDateBetween(String uid, LocalDate startDate, LocalDate endDate);

    Page<Transaction> findByUserIdAndDateBetween(String uid, LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<Transaction> findAllByAccountId(Long id);

    List<Transaction> findAllBySubcategoryId(Long id);

    List<Transaction> findAllByUserId(String uid);

    Page<Transaction> findAllByUserId(String uid, Pageable pageable);
//    @Query(value = "from Transaction t where date BETWEEN :startDate AND :endDate ")
//    Page<Transaction> findAllByUserIdDateRange(String uid, Pageable pageable,
//                                               @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
}
