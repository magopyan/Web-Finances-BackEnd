package com.webfinances.accounttype;

import com.webfinances.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeService {

    private final AccountTypeRepo accountTypeRepo;

    @Autowired
    public AccountTypeService(AccountTypeRepo accountTypeRepo) {
        this.accountTypeRepo = accountTypeRepo;
    }

    public List<AccountType> findAllAccountTypes() {
        return accountTypeRepo.findAll();
    }

    public AccountType findAccountTypeById(Long id) {
        return accountTypeRepo.findAccountTypeById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountType with ID " + id + " was not found!"));
    }
}
