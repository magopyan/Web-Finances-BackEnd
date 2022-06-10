package com.webfinances.account;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/validate-new")
    ResponseEntity<Map<String, String>> submitForm(@Valid @RequestBody AccountForm accountForm) {
        return new ResponseEntity<>(Collections.singletonMap("response", "Account form validated. ✔️"), HttpStatus.OK);
    }

    @PostMapping(value = "/validate-edit")
    ResponseEntity<Map<String, String>> submitEditForm(@Valid @RequestBody AccountForm accountForm) {
        return new ResponseEntity<>(Collections.singletonMap("response", "Account form validated. ✔️"), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(FirebaseAuthException.class)
    public String handleFirebaseExceptions(FirebaseAuthException ex) {
        return "Your session has expired. Please sign in again to refresh it.";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts(@RequestHeader (name="Authorization") String token) throws FirebaseAuthException {
        List<Account> accounts = accountService.findAllByUserId(token);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = "/all", params = {"page"})
    public ResponseEntity<List<Account>> getAllAccountsByPage(
            @RequestHeader (name="Authorization") String token,
            @RequestParam("page") int page) throws FirebaseAuthException {
        Page<Account> accountsPage = accountService.findAllByUserIdPage(token, page);
        if (page > accountsPage.getTotalPages()) {
            throw new RuntimeException("There are no accounts on page " + page + ".");
        }
        return new ResponseEntity<>(accountsPage.getContent(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
        Account account = accountService.findAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account, @RequestHeader (name="Authorization") String token) throws FirebaseAuthException {
        Account newAccount = accountService.addAccount(account, token);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    /// ADD TOKEN AS PARAMETER AND CHECK IF IT MATCHES THE TOKEN OF THE ACCOUNT TO EDIT
    /// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account,
                                                 @RequestHeader (name="Authorization") String token) throws Exception {
        Account updatedAccount = accountService.editAccount(account, token);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id, @RequestHeader (name="Authorization") String token) throws FirebaseAuthException {
        accountService.deleteAccount(id, token);
        System.out.println("deleted account with id "+id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
