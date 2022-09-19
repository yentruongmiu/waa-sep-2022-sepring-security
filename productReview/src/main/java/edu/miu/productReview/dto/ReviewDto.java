package edu.miu.productReview.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private int id;
    private String comment;
    private UserDto user;
    @JsonBackReference
    private ProductDto product;
}
