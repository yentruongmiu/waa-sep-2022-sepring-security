package edu.miu.productReview.controller;

import edu.miu.productReview.dto.UserDto;
import edu.miu.productReview.model.LoginRequest;
import edu.miu.productReview.model.LoginResponse;
import edu.miu.productReview.model.RefreshTokenRequest;
import edu.miu.productReview.service.UaaService;
import edu.miu.productReview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@RequiredArgsConstructor
@CrossOrigin
public class UaaController {

    private final UaaService uaaService;
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LoginRequest loginRequest) {
        var loginResponse = uaaService.signin(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return uaaService.refreshToken(refreshTokenRequest);
    }
}
