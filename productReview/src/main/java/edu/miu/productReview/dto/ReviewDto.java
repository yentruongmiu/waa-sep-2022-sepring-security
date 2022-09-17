package edu.miu.productReview.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private int id;
    private String comment;
    //@JsonBackReference
    private UserDto user;
}
