package com.webfinances.account;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.webfinances.accounttype.AccountTypeRepo;
import com.webfinances.exceptions.EntityNotFoundException;
import com.webfinances.exceptions.UniqueConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    
    private final AccountRepo accountRepo;
    private final AccountTypeRepo accountTypeRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo, AccountTypeRepo accountTypeRepo) {
        this.accountRepo = accountRepo;
        this.accountTypeRepo = accountTypeRepo;
    }

    public List<Account> findAllByUserId(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        return accountRepo.findAllByUserId(decodedToken.getUid());
    }

    public Page<Account> findAllByUserIdPage(String token, int pageNumber) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        Pageable pageable = PageRequest.of(pageNumber, 9);
        return accountRepo.findAllByUserId(decodedToken.getUid(), pageable);
    }

    public Account findAccountById(Long id) {
        return accountRepo.findAccountById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account with ID " + id + " was not found!"));
    }

    public Account addAccount(Account account, String token) throws FirebaseAuthException {
        Optional<Account> existingAccount = accountRepo.findAccountByName(account.getName());
        if(existingAccount.isPresent() /* i.e. != null */) {
            throw new UniqueConstraintException("You already have an existing account named " + account.getName() + "!");
        }
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        account.setUserId(decodedToken.getUid());
        return accountRepo.save(account);
    }

    public Account editAccount(Account account) {
//        accountTypeRepo.findAccountTypeById(account.getAccountType().getId())
//                .orElseThrow(new javax.persistence.EntityNotFoundException("The selected account type does not exist!"));

        List<Account> existingAccounts = accountRepo.findAllByName(account.getName());
        for(Account acc : existingAccounts) {
            if(acc.getId() != account.getId() && acc.getName() == account.getName()) {
                throw new UniqueConstraintException("You already have an existing account named " + account.getName() + "!");
            }
        }
        return accountRepo.save(account);
    }

    public void deleteAccount(Long id, String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        Optional<Account> existingAccount = accountRepo.findAccountById(id);
        if(!existingAccount.isPresent()) {
            return;
        }
        else {
            if(existingAccount.get().getUserId().equals(decodedToken.getUid())) {
                System.out.println("innnn");
                accountRepo.deleteById(id);
            }
        }
    }
}
