package com.mphasis.parent.api;

import com.mphasis.parent.entity.UserDetails;
import com.mphasis.parent.exception.InvalidCredentialsException;
import com.mphasis.parent.exception.InvalidTokenException;
import com.mphasis.parent.model.dto.AuthRequest;
import com.mphasis.parent.service.UserDetailService;
import com.mphasis.parent.utility.JwtUtil;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserDetailService userDetailService, JwtUtil jwtUtil) {
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    
    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody AuthRequest authRequest) {
        String userName = authRequest.getUsername();
        String password = authRequest.getPassword();

        try {
            userDetailService.validateUser(userName, password);
            String token = jwtUtil.generateToken(userName);
            validateToken(token);  
            HashMap<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(createErrorResponse("Invalid credentials!"));
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse("Token generation failed!"));
        }
    }

   
    @PostMapping("/register")
    public ResponseEntity<UserDetails> register(@RequestBody AuthRequest authRequest) {
        String userName = authRequest.getUsername();
        String password = authRequest.getPassword();

        try {
            UserDetails userDetails = userDetailService.registerUser(userName, password);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
    private void validateToken(String token) {
        if (token == null || token.isEmpty() || !jwtUtil.isTokenValid(token)) {
            throw new InvalidTokenException("Invalid or missing token!");
        }
    }

    
    private HashMap<String, String> createErrorResponse(String message) {
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }

    
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<HashMap<String, String>> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(createErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<HashMap<String, String>> handleInvalidToken(InvalidTokenException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse(ex.getMessage()));
    }
}
