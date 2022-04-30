package com.webfinances.transaction;

import com.webfinances.transaction.Transaction;
import com.webfinances.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.findTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/by-account")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(Long id) {
        List<Transaction> transactions = transactionService.findTransactionsByAccountId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/by-subcategory")
    public ResponseEntity<List<Transaction>> getTransactionsBySubcategoryId(Long id) {
        List<Transaction> transactions = transactionService.findTransactionsBySubcategoryId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.addTransaction(transaction);
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
