package com.jumong.E.TMotors.service.interfac;

import com.jumong.E.TMotors.dto.request.LoginRequest;
import com.jumong.E.TMotors.dto.request.RegisterRequest;
import com.jumong.E.TMotors.dto.response.LoginResponse;
import com.jumong.E.TMotors.dto.response.UserResponse;
import com.jumong.E.TMotors.model.User;

public interface UserService {

    UserResponse register(RegisterRequest registerRequest);

    UserResponse findUserById(Long firstUserId);

    LoginResponse login(LoginRequest loginRequest);

    User findByEmail(String actorEmail);
}
