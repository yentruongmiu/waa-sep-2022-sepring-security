package edu.miu.productReview.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.productReview.domain.Category;
import edu.miu.productReview.domain.Review;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {
    private int id;
    private String name;
    private Float price;
    private Float rating;

    private CategoryDto category;

    private List<ReviewDto> reviews;
}
