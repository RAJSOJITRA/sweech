package com.sweech.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull
    @Email(message = "ID must be a valid email address.")
    private String id;

    @NotNull
    private String password;

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
}
