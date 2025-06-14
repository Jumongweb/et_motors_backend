package com.jumong.E.TMotors.controller;

import com.jumong.E.TMotors.dto.request.LoginRequest;
import com.jumong.E.TMotors.dto.request.RegisterRequest;
import com.jumong.E.TMotors.exception.CarException;
import com.jumong.E.TMotors.service.interfac.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth/")
@Slf4j
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
            log.info("Received login request: {}", loginRequest);
            return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
        } catch (CarException exception){
            log.error("Login failed: {}", exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error during login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }

}
