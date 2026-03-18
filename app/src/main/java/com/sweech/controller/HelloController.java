package com.sweech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/hello", produces = "application/json")
public class HelloController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> hello() {
        return ResponseEntity.ok(Collections.singletonMap("message", "Hello!"));
    }
}
