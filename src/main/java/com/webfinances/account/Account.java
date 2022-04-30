package com.webfinances.account;

import com.webfinances.accounttype.AccountType;
import com.webfinances.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Account")
@Table(
        name = "account",
        uniqueConstraints = { @UniqueConstraint(name = "account_name_unique", columnNames = "name") }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "account_sequence"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "balance",
            nullable = false,
            scale = 2
    )
    private Double balance;

    @Column(
            name = "image_url",
            nullable = false
    )
    private String imageUrl;

    @ManyToOne
    @JoinColumn(
            name = "account_type_id",
            referencedColumnName = "id",
            nullable = false
    )
    private AccountType accountType;

    @OneToMany(
            mappedBy = "account",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactionList;


    public Account(String name, Double balance, String imageUrl, AccountType accountType) {
        this.name = name;
        this.balance = balance;
        this.imageUrl = imageUrl;
        this.accountType = accountType;
    }

    public Account(String name, Double balance, String imageUrl, AccountType accountType, List<Transaction> transactionList) {
        this.name = name;
        this.balance = balance;
        this.imageUrl = imageUrl;
        this.accountType = accountType;
        this.transactionList = transactionList;
    }
}