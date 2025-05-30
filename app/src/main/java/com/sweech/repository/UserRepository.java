package com.sweech.repository;

import com.sweech.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserRepository {

    @Select("SELECT COUNT(*) > 0 FROM member WHERE id = #{id}")
    boolean existsById(String id);

    @Insert("INSERT INTO member (id, password, username, registration_time) VALUES (#{id}, #{password}, #{username}, #{registrationTime})")
    void save(User user);

    @Select("SELECT * FROM member WHERE id = #{id}")
    User findById(@Param("id") String id);

    @Update("UPDATE member SET password = #{password}, username = #{username} WHERE id = #{id}")
    void update(User user);
}
