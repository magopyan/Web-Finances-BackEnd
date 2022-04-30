package com.webfinances.account;

import com.webfinances.exceptions.EntityNotFoundException;
import com.webfinances.exceptions.UniqueConstraintException;
import com.webfinances.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    
    private final AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public List<Account> findAllAccounts() {
        return accountRepo.findAll();
    }

    public Account findAccountById(Long id) {
        return accountRepo.findAccountById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account with ID " + id + " was not found!"));
    }

    public Account addAccount(Account account) {
        Optional<Account> existingAccount = accountRepo.findAccountByName(account.getName());
        if(existingAccount.isPresent() /* i.e. != null */) {
            throw new UniqueConstraintException("You already have an existing account named " + account.getName() + "!");
        }
        return accountRepo.save(account);
    }

    public Account editAccount(Account account) {
        List<Account> existingAccounts = accountRepo.findAllByName(account.getName());
        for(Account acc : existingAccounts) {
            if(acc.getId() != account.getId() && acc.getName() == account.getName()) {
                throw new UniqueConstraintException("You already have an existing account named " + account.getName() + "!");
            }
        }
        return accountRepo.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepo.deleteAccountById(id);
    }
}
