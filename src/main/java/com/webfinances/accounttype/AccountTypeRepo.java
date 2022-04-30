package com.webfinances.accounttype;

import com.webfinances.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AccountTypeRepo extends JpaRepository<AccountType, Long> {

    @Transactional
    void deleteAccountTypeById(Long id);

    Optional<AccountType> findAccountTypeById(Long id);
}
