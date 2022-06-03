package com.webfinances.categorytype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webfinances.account.Account;
import com.webfinances.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "CategoryType")
@Table(name = "category_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryType {
    @Id
    @SequenceGenerator(
            name = "category_type_sequence",
            sequenceName = "category_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_type_sequence"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "coefficient",
            nullable = false
    )
    private Integer coefficient;

    @Column(
            name = "image_url",
            nullable = false
    )
    private String imageUrl;

    @OneToMany(
            mappedBy = "categoryType",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Category> categoryList;


    public CategoryType(String name, Integer coefficient, String imageUrl) {
        this.name = name;
        this.coefficient = coefficient;
        this.imageUrl = imageUrl;
    }

    public CategoryType(String name, Integer coefficient, String imageUrl, List<Category> categoryList) {
        this.name = name;
        this.coefficient = coefficient;
        this.imageUrl = imageUrl;
        this.categoryList = categoryList;
    }
}
