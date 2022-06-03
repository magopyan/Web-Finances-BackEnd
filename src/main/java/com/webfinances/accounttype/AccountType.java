package com.webfinances.accounttype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webfinances.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "AccountType")
@Table(name = "account_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountType {
    @Id
    @SequenceGenerator(
            name = "account_type_sequence",
            sequenceName = "account_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "account_type_sequence"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "image_url",
            nullable = false
    )
    private String imageUrl;

    @OneToMany(
            mappedBy = "accountType",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Account> accountList;


    public AccountType(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public AccountType(String name, String imageUrl, List<Account> accountList) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.accountList = accountList;
    }
}
