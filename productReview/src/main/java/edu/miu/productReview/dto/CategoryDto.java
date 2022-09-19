package edu.miu.productReview.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.productReview.domain.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private int id;
    private String name;
    @JsonManagedReference
    private List<ProductDto> categories;
}
