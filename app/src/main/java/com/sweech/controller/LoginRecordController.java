package com.sweech.controller;

import com.sweech.service.LoginRecordService;
import com.sweech.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/login-records", produces = "application/json")
public class LoginRecordController {

    @Autowired
    private LoginRecordService loginRecordService;

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getLoginRecords(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(loginRecordService.getLoginRecords(userId));
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
