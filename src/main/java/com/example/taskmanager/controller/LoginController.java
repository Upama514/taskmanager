package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.payload.LoginRequest;
import com.example.taskmanager.security.JwtUtil;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User user = userService.findByUsername(request.getUsername());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return "Bearer " + token; // return token with "Bearer" prefix
        } else {
            return "Invalid username or password";
        }
    }
}
