package edu.miu.productReview.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private AddressDto address;
    //@JsonManagedReference
    private Set<RoleDto> roles;
}
