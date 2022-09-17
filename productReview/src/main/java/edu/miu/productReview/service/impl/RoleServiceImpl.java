package edu.miu.productReview.service.impl;

import edu.miu.productReview.domain.Role;
import edu.miu.productReview.dto.RoleDto;
import edu.miu.productReview.repo.RoleRepo;
import edu.miu.productReview.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    private final ModelMapper mapper;

    @Override
    public RoleDto save(RoleDto role) {
        System.out.println(role);
        return mapper.map(roleRepo.save(mapper.map(role, Role.class)), RoleDto.class);
    }

    @Override
    public RoleDto findById(int id) {
        System.out.println(id);

        var result = roleRepo.findById(id).get();
        System.out.println(result);
        return mapper.map(result, RoleDto.class);
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = new ArrayList<>();
        roleRepo.findAll().forEach(roles::add);
        return roles.stream().map(r -> mapper.map(r, RoleDto.class)).toList();
    }

    @Override
    public void delete(int id) {
        roleRepo.deleteById(id);
    }
}
