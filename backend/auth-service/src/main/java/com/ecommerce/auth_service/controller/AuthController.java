package com.ecommerce.auth_service.controller;

import java.util.Map;
import java.util.UUID;

// import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth_service.dto.LoginRequest;
import com.ecommerce.auth_service.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

@Autowired
private JwtUtil jwtUtil;
private Map<String, String> refreshTokenStore = new java.util.concurrent.ConcurrentHashMap<>();
/*
@PostMapping("/login")
public String login(@RequestBody LoginRequest request) {

    /*
    System.out.println("----- LOGIN REQUEST DETAILS -----");
    System.out.println("Username: " + request.getUsername());
    System.out.println("Password: " + request.getPassword());
    System.out.println("----- END OF DETAILS -----");
    */
   
/*/
    if ("logesh".equals(request.getUsername())
            && "dharshu".equals(request.getPassword())) {
        System.out.println("Logesh says Generating token for user: " + request.getUsername());
        return jwtUtil.generateToken("logesh", "ROLE_USER");
    }

    throw new RuntimeException("Invalid credentials");
    }
 */

        @PostMapping("/login")
        public Map<String, String> login(@RequestBody LoginRequest request) 
        {

            String accessToken = jwtUtil.generateToken("logesh", "ROLE_USER");
            String refreshToken = UUID.randomUUID().toString();

            refreshTokenStore.put(refreshToken, "logesh");

            return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
            );
        }

        @PostMapping("/refresh")
        public Map<String, String> refresh(@RequestParam String refreshToken) {

            String username = refreshTokenStore.get(refreshToken);

            if (username == null) {
                throw new RuntimeException("Invalid refresh token");
            }

            String newAccessToken =
                jwtUtil.generateToken(username, "ROLE_USER");

            return Map.of("accessToken", newAccessToken);
        }

        @PostMapping("/logout")
        public String logout(@RequestParam String refreshToken) {
            refreshTokenStore.remove(refreshToken);
            return "Logged out successfully";
        }


}
