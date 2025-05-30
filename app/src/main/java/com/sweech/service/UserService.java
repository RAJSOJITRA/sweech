package com.sweech.service;

import com.sweech.model.User;
import com.sweech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        if (userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("ID already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationTime(LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    public void updateUser(String id, Map<String, Object> updates) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        if (updates.containsKey("password")) {
            String password = (String) updates.get("password");
            if (password == null || !password.matches("^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,20}$")) {
                throw new IllegalArgumentException("Password must be between 12 and 20 characters, containing lowercase letters, numbers, and special characters.");
            }
            user.setPassword(passwordEncoder.encode(password));
        }

        if (updates.containsKey("username")) {
            String username = (String) updates.get("username");
            if (username == null || !username.matches("^[가-힣]{1,10}$")) {
                throw new IllegalArgumentException("Username must be in Korean and between 1 and 10 characters.");
            }
            user.setUsername(username);
        }

        userRepository.update(user);
    }
}
