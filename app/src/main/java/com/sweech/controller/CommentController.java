package com.sweech.controller;

import com.sweech.model.Comment;
import com.sweech.service.CommentService;
import com.sweech.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/comments", produces = "application/json")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createComment(@Valid @RequestBody Comment comment) {
        try {
            Comment createdComment = commentService.createComment(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> listComments(@RequestParam Long postId, @RequestParam(required = false) Long cursor) {
        try {
            Map<String, Object> response = commentService.listComments(postId, cursor);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long id, @RequestParam String userId) {
        try {
            commentService.deleteComment(id, userId);
            return ResponseEntity.ok(Map.of("message", "Comment deleted successfully."));
        } catch (IllegalArgumentException e) {
            return ResponseUtil.buildErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
