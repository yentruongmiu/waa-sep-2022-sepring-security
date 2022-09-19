package edu.miu.productReview.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String comment;

    //a review can only belong to one user
    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
}
