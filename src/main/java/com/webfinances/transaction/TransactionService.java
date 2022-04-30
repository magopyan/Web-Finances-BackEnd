package com.webfinances.transaction;

import com.webfinances.exceptions.EntityNotFoundException;
import com.webfinances.exceptions.UniqueConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction findTransactionById(Long id) {
        return transactionRepo.findTransactionById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with ID " + id + " was not found!"));
    }

    public List<Transaction> findTransactionsByAccountId(Long id) {
        return transactionRepo.findAllByAccountId(id);
    }

    public List<Transaction> findTransactionsBySubcategoryId(Long id) {
        return transactionRepo.findAllBySubcategoryId(id);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public Transaction editTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepo.deleteTransactionById(id);
    }
}
