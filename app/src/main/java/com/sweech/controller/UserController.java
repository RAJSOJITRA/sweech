package com.sweech.controller;

import com.sweech.model.User;
import com.sweech.service.UserService;
import com.sweech.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("id", registeredUser.getId());
            response.put("username", registeredUser.getUsername());
            response.put("password", user.getPassword()); // Include password in the response
            response.put("registrationTime", registeredUser.getRegistrationTime().format(DateTimeFormatter.ISO_DATE_TIME));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateUser(@RequestBody Map<String, Object> updates) {
        try {
            String id = (String) updates.get("id");
            if (id == null || id.isEmpty()) {
                return ResponseUtil.buildErrorResponse("User ID is required.", HttpStatus.BAD_REQUEST);
            }

            userService.updateUser(id, updates);
            return ResponseEntity.ok(Map.of("message", "User information updated successfully."));
        } catch (IllegalArgumentException e) {
            return ResponseUtil.buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
