package com.webfinances.transaction;

import com.google.firebase.auth.FirebaseAuthException;
import com.webfinances.account.Account;
import com.webfinances.account.AccountForm;
import com.webfinances.transaction.Transaction;
import com.webfinances.transaction.TransactionService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/validate-new")
    ResponseEntity<Map<String, String>> submitForm(@Valid @RequestBody TransactionForm transactionForm) {
        return new ResponseEntity<>(Collections.singletonMap("response", "Transaction form validated. ✔️"), HttpStatus.OK);
    }

    @PostMapping(value = "/validate-edit")
    ResponseEntity<Map<String, String>> submitEditForm(@Valid @RequestBody TransactionForm transactionForm) {
        return new ResponseEntity<>(Collections.singletonMap("response", "Transaction form validated. ✔️"), HttpStatus.OK);
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
    public ResponseEntity<List<Transaction>> getAllTransactions(@RequestHeader (name="Authorization") String token)
            throws FirebaseAuthException {
        List<Transaction> transactions = transactionService.findAllByUserId(token);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping(value = "/all", params = {"page"})
    public ResponseEntity<List<Transaction>> getAllTransactionsByPage(
            @RequestHeader (name="Authorization") String token,
            @RequestParam("page") int page) throws FirebaseAuthException {
        Page<Transaction> transactionsPage = transactionService.findAllByUserIdPage(token, page);
        if (page > transactionsPage.getTotalPages()) {
            throw new RuntimeException("There are no accounts on page " + page + ".");
        }
        return new ResponseEntity<>(transactionsPage.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "/by-date-all")
    public ResponseEntity<List<Transaction>> getAllTransactionsByDateRange(
            @RequestHeader (name="Authorization") String token,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) throws FirebaseAuthException {
        List<Transaction> transactions = transactionService.findAllByUserIdDateRange(token, startDate, endDate);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping(value = "/by-date")
    public ResponseEntity<List<Transaction>> getAllTransactionsByDateRangePage(
            @RequestHeader (name="Authorization") String token,
            @RequestParam("page") int page,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) throws FirebaseAuthException {
        Page<Transaction> transactionsPage = transactionService.findAllByUserIdDateRangePage(token, page, startDate, endDate);
        if (page > transactionsPage.getTotalPages()) {
            throw new RuntimeException("There are no accounts on page " + page + ".");
        }
        return new ResponseEntity<>(transactionsPage.getContent(), HttpStatus.OK);
    }

//    @GetMapping(value = "/by-date"
//            // params = {"startDate, endDate"}
//    )
//    public ResponseEntity<List<Transaction>> getAllTransactionsByDateRange(
//            @RequestHeader (name="Authorization") String token
////            @RequestParam("startDate") String startDate,
////            @RequestParam("endDate") String endDate
//    ) throws FirebaseAuthException {
//        List<Transaction> transactions = transactionService.findAllByUserIdDateRange(
//                token, LocalDate.parse("2022-06-01"), LocalDate.parse("2022-06-03"));
//        return new ResponseEntity<>(transactions, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/by-date", params = {"page"}
//            //, params = {"page, startDate, endDate"}
//    )
//    public ResponseEntity<List<Transaction>> getAllTransactionsByDateRangePage(
//            @RequestHeader (name="Authorization") String token,
//            @RequestParam("page") int page
////            @RequestParam("startDate") String startDate,
////            @RequestParam("endDate") String endDate
//    ) throws FirebaseAuthException {
//        Page<Transaction> transactionsPage = transactionService.findAllByUserIdDateRangePage(
//                token, page, LocalDate.parse("2022-06-01"), LocalDate.parse("2022-06-03"));
//        if (page > transactionsPage.getTotalPages()) {
//            throw new RuntimeException("There are no accounts on page " + page + ".");
//        }
//        return new ResponseEntity<>(transactionsPage.getContent(), HttpStatus.OK);
//    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.findTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping(value = "/by-account", params = {"id"})
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@RequestParam("id") Long id) {
        List<Transaction> transactions = transactionService.findTransactionsByAccountId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/by-subcategory")
    public ResponseEntity<List<Transaction>> getTransactionsBySubcategoryId(Long id) {
        List<Transaction> transactions = transactionService.findTransactionsBySubcategoryId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction,
                                                      @RequestHeader (name="Authorization") String token) throws FirebaseAuthException {
        Transaction newTransaction = transactionService.addTransaction(transaction, token);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) {
        Transaction updatedTransaction = transactionService.editTransaction(transaction);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
