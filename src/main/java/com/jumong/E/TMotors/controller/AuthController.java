package com.jumong.E.TMotors.controller;

import com.jumong.E.TMotors.dto.request.LoginRequest;
import com.jumong.E.TMotors.dto.request.RegisterRequest;
import com.jumong.E.TMotors.exception.CarException;
import com.jumong.E.TMotors.service.interfac.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth/")
public class AuthController {

    private final UserService userService;


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try{
            return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED);
        } catch (CarException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try{
            return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
        } catch (CarException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
