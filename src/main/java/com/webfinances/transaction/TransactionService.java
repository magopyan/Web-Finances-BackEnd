package com.webfinances.transaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.webfinances.exceptions.EntityNotFoundException;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
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

    public List<Transaction> findAllByUserId(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        return transactionRepo.findAllByUserId(decodedToken.getUid());
    }

    public Page<Transaction> findAllByUserIdPage(String token, int pageNumber) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        Pageable pageable = PageRequest.of(pageNumber, 15);
        return transactionRepo.findAllByUserId(decodedToken.getUid(), pageable);
    }

    public List<Transaction> findAllByUserIdDateRange(String token, LocalDate startDate, LocalDate endDate)
            throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        return transactionRepo.findByUserIdAndDateBetween(decodedToken.getUid(), startDate, endDate);
    }

    public Page<Transaction> findAllByUserIdDateRangePage(String token, int pageNumber, LocalDate startDate, LocalDate endDate)
            throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        Pageable pageable = PageRequest.of(pageNumber, 15);
        return transactionRepo.findByUserIdAndDateBetween(decodedToken.getUid(), startDate, endDate, pageable);
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
