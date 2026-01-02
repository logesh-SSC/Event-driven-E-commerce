package com.ecommerce.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth_service.dto.LoginRequest;
import com.ecommerce.auth_service.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

@Autowired
private JwtUtil jwtUtil;

@PostMapping("/login")
public String login(@RequestBody LoginRequest request) {

    if ("logesh".equals(request.getUsername())
            && "dharshu".equals(request.getPassword())) {
        System.out.println("Logesh says Generating token for user: " + request.getUsername());
        return jwtUtil.generateToken("logesh", "ROLE_USER");
    }

    throw new RuntimeException("Invalid credentials");
}

}
