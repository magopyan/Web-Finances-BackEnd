package com.webfinances.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webfinances.categorytype.CategoryType;
import com.webfinances.subcategory.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Category")
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_sequence"
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
            name = "category_type_id",
            referencedColumnName = "id",
            nullable = false
    )
    private CategoryType categoryType;

    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Subcategory> subcategoryList;


    public Category(String name, String imageUrl, CategoryType categoryType) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.categoryType = categoryType;
    }

    public Category(String name, String imageUrl, CategoryType categoryType, List<Subcategory> subcategoryList) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.categoryType = categoryType;
        this.subcategoryList = subcategoryList;
    }
}
