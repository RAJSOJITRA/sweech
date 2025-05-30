package com.sweech.service;

import com.sweech.model.User;
import com.sweech.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRecordService loginRecordService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String jwtSecret = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
    private final long jwtExpirationMs = 20 * 60 * 1000;

    public String authenticate(String id, String password, HttpServletRequest request) {
        User user = userRepository.findById(id);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid ID or password.");
        }

        String ipAddress = request.getRemoteAddr();
        loginRecordService.recordLogin(id, ipAddress);

        return generateJwtToken(id);
    }

    private String generateJwtToken(String id) {
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
