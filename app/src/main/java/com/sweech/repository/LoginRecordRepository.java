package com.sweech.repository;

import com.sweech.model.LoginRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoginRecordRepository {

    @Insert("INSERT INTO login_records (user_id, login_time, ip_address) " +
            "VALUES (#{userId}, NOW(), #{ipAddress})")
    void saveLoginRecord(@Param("userId") String userId, @Param("ipAddress") String ipAddress);

    @Select("SELECT lr.user_id, u.username, lr.login_time, lr.ip_address " +
            "FROM login_records lr " +
            "LEFT JOIN member u ON lr.user_id = u.id " +
            "WHERE lr.user_id = #{userId} " +
            "ORDER BY lr.login_time DESC " +
            "LIMIT 30")
    List<LoginRecord> findLoginRecordsByUserId(@Param("userId") String userId);
}
