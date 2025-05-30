package com.sweech.service;

import com.sweech.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    public List<Map<String, Object>> getLoginCountRankings() {
        return rankingRepository.findLoginCountRankings();
    }
}
