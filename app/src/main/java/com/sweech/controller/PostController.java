package com.sweech.controller;

import com.sweech.model.Post;
import com.sweech.service.PostService;
import com.sweech.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@Valid @RequestBody Post post) {
        try {
            Post createdPost = postService.createPost(post);
            Map<String, Object> response = new HashMap<>();
            response.put("id", createdPost.getId());
            response.put("title", createdPost.getTitle());
            response.put("content", createdPost.getContent());
            response.put("username", createdPost.getUsername()); 
            response.put("createdAt", createdPost.getCreatedAt());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> listPosts(@RequestParam(defaultValue = "1") int page) {
        try {
            Map<String, Object> response = postService.listPosts(page);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostDetails(@PathVariable Long id) {
        try {
            Map<String, Object> postDetails = postService.getPostDetails(id);
            return ResponseEntity.ok(postDetails);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
