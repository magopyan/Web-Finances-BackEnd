package com.webfinances.transaction;

import com.webfinances.account.Account;
import com.webfinances.subcategory.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Transaction")
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "transaction_sequence"
    )
    private Long id;

    @Column(
            name = "amount",
            nullable = false
    )
    private Double amount;

    @Column(
            name = "note",
            nullable = false
    )
    private String note;

    @Column(
            name = "date",
            nullable = false
    )
    private LocalDate date;

    @ManyToOne
    @JoinColumn(
            name = "subcategory_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Subcategory subcategory;

    @ManyToOne
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Account account;


    public Transaction(Double amount, String note, LocalDate date, Subcategory subcategory, Account account) {
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.subcategory = subcategory;
        this.account = account;
    }
}
