package edu.miu.productReview.service.impl;

import edu.miu.productReview.model.LoginRequest;
import edu.miu.productReview.model.LoginResponse;
import edu.miu.productReview.model.RefreshTokenRequest;
import edu.miu.productReview.security.JwtHelper;
import edu.miu.productReview.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {

    private final JwtHelper jwtHelper;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse signin(LoginRequest loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
        }

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        var loginResponse = new LoginResponse(accessToken,  refreshToken);

        return loginResponse;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            return loginResponse;
        }
        return new LoginResponse();
    }
}
