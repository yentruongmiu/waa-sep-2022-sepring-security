package edu.miu.productReview.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddressDto {
    private int id;
    private String street;
    private String zip;
    private String city;
}

