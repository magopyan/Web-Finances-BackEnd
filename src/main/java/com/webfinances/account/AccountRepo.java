package com.webfinances.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {

    List<Account> findAllByUserId(String uid);

    Optional<Account> findAccountById(Long id);

    Optional<Account> findAccountByName(String name);

    List<Account> findAllByName(String name);

    // PAGINATION
    Page<Account> findAllByUserId(String uid, Pageable pageable);
}
