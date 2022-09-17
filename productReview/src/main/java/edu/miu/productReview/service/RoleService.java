package edu.miu.productReview.service;

import edu.miu.productReview.domain.Role;
import edu.miu.productReview.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto save(RoleDto role);
    RoleDto findById(int id);
    List<RoleDto> findAll();
    void delete(int id);
}
