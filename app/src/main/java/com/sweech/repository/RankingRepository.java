package com.sweech.repository;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RankingRepository {

    @Select("SELECT u.username AS name, " +
            "COUNT(lr.id) AS loginCount, " +
            "RANK() OVER (ORDER BY COUNT(lr.id) DESC) AS rank " +
            "FROM login_records lr " +
            "LEFT JOIN member u ON lr.user_id = u.id " +
            "WHERE lr.login_time >= DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) " +
            "AND lr.login_time < DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 7 DAY) " +
            "GROUP BY lr.user_id " +
            "ORDER BY rank " +
            "LIMIT 20")
    List<Map<String, Object>> findLoginCountRankings();
}
