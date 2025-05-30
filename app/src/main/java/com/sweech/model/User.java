package com.sweech.model;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class User {

    @NotNull
    @Email(message = "ID must be a valid email address.")
    private String id;

    @NotNull
    @Size(min = 12, max = 20, message = "Password must be between 12 and 20 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", 
             message = "Password must contain lowercase letters, numbers, and special characters.")
    private String password;

    @NotNull
    @Pattern(regexp = "^[가-힣]{1,10}$", message = "Username must be in Korean and between 1 and 10 characters.")
    private String username;

    private LocalDateTime registrationTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }
    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }
}