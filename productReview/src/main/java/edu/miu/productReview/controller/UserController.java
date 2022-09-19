package edu.miu.productReview.controller;

import edu.miu.productReview.dto.RoleDto;
import edu.miu.productReview.dto.UserDto;
import edu.miu.productReview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/{id}/roles")
    public UserDto addRole(@PathVariable int id, @RequestBody RoleDto role) {
        return userService.addRole(id, role);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable int id, @RequestBody UserDto user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }
}
