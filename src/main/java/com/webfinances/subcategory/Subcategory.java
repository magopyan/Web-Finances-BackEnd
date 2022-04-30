package com.webfinances.subcategory;

import com.webfinances.category.Category;
import com.webfinances.categorytype.CategoryType;
import com.webfinances.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Subcategory")
@Table(name = "subcategory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subcategory {
    @Id
    @SequenceGenerator(
            name = "subcategory_sequence",
            sequenceName = "subcategory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "subcategory_sequence"
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

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Category category;

    @OneToMany(
            mappedBy = "subcategory",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactionList;


    public Subcategory(String name, String imageUrl, Category category) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Subcategory(String name, String imageUrl, Category category, List<Transaction> transactionList) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.transactionList = transactionList;
    }
}
