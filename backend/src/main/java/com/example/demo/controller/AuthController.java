package com.example.cms.controller;

import com.example.cms.model.User;
import com.example.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> loginData) {

        Optional<User> user = service.login(
                loginData.get("email"),
                loginData.get("password")
        );

        if (user.isPresent()) {
            return Map.of(
                    "message", "Login Successful",
                    "role", user.get().getRole()
            );
        }

        return "Invalid Credentials";
    }
}