package edu.miu.productReview.service;

import edu.miu.productReview.dto.RoleDto;
import edu.miu.productReview.dto.UserDto;
import edu.miu.productReview.model.LoginRequest;
import edu.miu.productReview.model.LoginResponse;
import edu.miu.productReview.model.RefreshTokenRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto save(UserDto user);
    List<UserDto> findAll();
    UserDto findById(int id);
    UserDto update(int id, UserDto user);
    void delete(int id);
    UserDto addRole(int id, RoleDto role);
}
