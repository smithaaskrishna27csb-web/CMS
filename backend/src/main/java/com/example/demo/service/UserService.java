package com.example.cms.service;

import com.example.cms.model.User;
import com.example.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return "User Registered Successfully!";
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = repo.findByEmail(email);
        if (user.isPresent() &&
            passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
}