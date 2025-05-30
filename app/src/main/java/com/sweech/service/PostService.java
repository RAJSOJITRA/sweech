package com.sweech.service;

import com.sweech.model.Post;
import com.sweech.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.sweech.model.User;
import com.sweech.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${pagination.page-size:20}")
    private int pageSize;

    public Post createPost(Post post) {
        User user = userRepository.findById(post.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        post.setUsername(user.getUsername());
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return post;
    }

    public Map<String, Object> listPosts(int page) {
        if (page < 1) {
            throw new IllegalArgumentException("Page number must be greater than or equal to 1.");
        }

        int offset = (page - 1) * pageSize;
        List<Post> posts = postRepository.findPosts(offset, pageSize);
        int totalPosts = postRepository.countPosts();

        Map<String, Object> response = new HashMap<>();
        response.put("posts", posts);
        response.put("totalPosts", totalPosts);
        response.put("currentPage", page);
        response.put("totalPages", (int) Math.ceil((double) totalPosts / pageSize));

        return response;
    }

    public Map<String, Object> getPostDetails(Long id) {
        Post post = postRepository.findPostById(id);
        if (post == null) {
            throw new IllegalArgumentException("Post not found.");
        }

        return Map.of(
            "id", post.getId(),
            "title", post.getTitle(),
            "content", post.getContent(),
            "username", post.getUsername(),
            "createdAt", post.getCreatedAt()
        );
    }
}
