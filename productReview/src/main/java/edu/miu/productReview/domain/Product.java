package edu.miu.productReview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;
    private Float price;
    private Float rating;

    //a product can belong to only one category
    @ManyToOne
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "product") //on column, bidirection
    @JsonManagedReference
    private List<Review> reviews;
}
