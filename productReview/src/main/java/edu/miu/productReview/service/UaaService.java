package edu.miu.productReview.service;

import edu.miu.productReview.model.LoginRequest;
import edu.miu.productReview.model.LoginResponse;
import edu.miu.productReview.model.RefreshTokenRequest;

public interface UaaService {

    LoginResponse signin(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
