package com.webfinances.transaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.webfinances.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

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

    public Transaction addTransaction(Transaction transaction, String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        transaction.setUserId(decodedToken.getUid());
        return transactionRepo.save(transaction);
    }

    public Transaction editTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepo.deleteTransactionById(id);
    }
}
