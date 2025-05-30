package com.sweech.service;

import com.sweech.model.Comment;
import com.sweech.model.User;
import com.sweech.repository.CommentRepository;
import com.sweech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    public Comment createComment(Comment comment) {
        User user = userRepository.findById(comment.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        comment.setUsername(user.getUsername());
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return comment;
    }

    public Map<String, Object> listComments(Long postId, Long cursor) {
        List<Comment> comments = commentRepository.findComments(postId, cursor, 10);
        Long nextCursor = comments.isEmpty() ? null : comments.get(comments.size() - 1).getId();

        Map<String, Object> response = new HashMap<>();
        response.put("comments", comments);
        response.put("nextCursor", nextCursor);
        return response;
    }

    public void deleteComment(Long id, String userId) {
        Comment comment = commentRepository.findById(id);
        if (comment == null) {
            throw new IllegalArgumentException("Comment not found.");
        }

        if (!comment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("You are not authorized to delete this comment.");
        }

        commentRepository.delete(id);
    }
}
