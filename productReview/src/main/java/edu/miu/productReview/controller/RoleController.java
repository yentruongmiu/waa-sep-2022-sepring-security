package edu.miu.productReview.controller;

import edu.miu.productReview.domain.Role;
import edu.miu.productReview.dto.RoleDto;
import edu.miu.productReview.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public RoleDto save(@RequestBody RoleDto role) {
        System.out.println("controller:" + role);
        return roleService.save(role);
    }

    @GetMapping
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleDto findById(@PathVariable int id) {
        return roleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        roleService.delete(id);
    }
}
