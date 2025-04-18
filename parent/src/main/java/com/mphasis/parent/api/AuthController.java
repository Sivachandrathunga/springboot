package com.mphasis.parent.api;

import com.mphasis.parent.entity.UserDetails;
import com.mphasis.parent.exception.InvalidCredentialsException;
import com.mphasis.parent.exception.InvalidTokenException;
import com.mphasis.parent.model.dto.AuthRequest;
import com.mphasis.parent.model.dto.AuthResponse;
import com.mphasis.parent.service.UserDetailService;
import com.mphasis.parent.utility.JwtUtil;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/auth")
public class AuthController {
 
    private UserDetailService userDetailService;
    private JwtUtil jwtUtil;
    
    @Autowired
    public AuthController(UserDetailService userDetailService, JwtUtil jwtUtil) {
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }
 
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        String userName = authRequest.getUsername();
        String password = authRequest.getPassword();
 
        try {
            userDetailService.validateUser(userName, password);
            String token = jwtUtil.generateToken(userName);
            if (token == null || token.isEmpty()) {
                throw new InvalidTokenException("Token generation failed!");
            }
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            throw new InvalidCredentialsException("Invalid username or password!");
        }
    }

 
    @PostMapping("/register")
    public ResponseEntity<UserDetails> register(@RequestBody AuthRequest authRequest) {
        String userName = authRequest.getUsername();
        String password = authRequest.getPassword();
 
        try {
            UserDetails userDetails = userDetailService.registerUser(userName, password);
            return ResponseEntity.ok(userDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    private void validateToken(String token) {
        if (token == null || token.isEmpty() || !jwtUtil.isTokenValid(token)) {
            throw new IllegalArgumentException("Invalid or missing token!");
        }
    }
}