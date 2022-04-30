package com.webfinances.accounttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account-types")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @Autowired
    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        List<AccountType> accountTypes = accountTypeService.findAllAccountTypes();
        return new ResponseEntity<>(accountTypes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AccountType> getAccountTypeById(@PathVariable("id") Long id) {
        AccountType accountType = accountTypeService.findAccountTypeById(id);
        return new ResponseEntity<>(accountType, HttpStatus.OK);
    }
}
