package com.webfinances.account;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {

    @Transactional
    void deleteAccountById(Long id);

    Optional<Account> findAccountById(Long id);

    Optional<Account> findAccountByName(String name);

    List<Account> findAllByName(String name);
}
