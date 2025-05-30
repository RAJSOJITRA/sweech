package com.sweech.repository;

import com.sweech.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentRepository {

    @Insert("INSERT INTO comments (content, post_id, user_id, created_at) VALUES (#{content}, #{postId}, #{userId}, #{createdAt})")
    void save(Comment comment);

    @Select("SELECT c.id, c.content, u.username, c.created_at " +
            "FROM comments c " +
            "JOIN member u ON c.user_id = u.id " +
            "WHERE c.post_id = #{postId} " +
            "AND (#{cursor} IS NULL OR c.id < #{cursor}) " +
            "ORDER BY c.created_at DESC " +
            "LIMIT #{limit}")
    List<Comment> findComments(@Param("postId") Long postId, @Param("cursor") Long cursor, @Param("limit") int limit);

    @Select("SELECT * FROM comments WHERE id = #{id}")
    Comment findById(@Param("id") Long id);

    @Delete("DELETE FROM comments WHERE id = #{id}")
    void delete(@Param("id") Long id);
}
