package com.sweech.repository;

import com.sweech.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostRepository {

    @Insert("INSERT INTO posts (title, content, user_id, created_at) VALUES (#{title}, #{content}, #{userId}, #{createdAt})")
    void save(Post post);

    @Select("SELECT p.id, p.title, u.username, p.created_at " +
            "FROM posts p " +
            "JOIN member u ON p.user_id = u.id " +
            "ORDER BY p.created_at DESC " +
            "LIMIT #{limit} OFFSET #{offset}")
    List<Post> findPosts(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM posts")
    int countPosts();

    @Select("SELECT p.id, p.title, p.content, u.username, p.created_at " +
            "FROM posts p " +
            "JOIN member u ON p.user_id = u.id " +
            "WHERE p.id = #{id}")
    Post findPostById(@Param("id") Long id);
}
