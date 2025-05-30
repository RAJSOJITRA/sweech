package com.sweech.controller;

import com.sweech.service.RankingService;
import com.sweech.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/rankings", produces = "application/json")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/login-count")
    public ResponseEntity<Object> getLoginCountRankings() {
        try {
            return ResponseEntity.ok(rankingService.getLoginCountRankings());
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
