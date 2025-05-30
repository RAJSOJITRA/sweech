package com.sweech.service;

import com.sweech.model.LoginRecord;
import com.sweech.repository.LoginRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginRecordService {

    @Autowired
    private LoginRecordRepository loginRecordRepository;

    public List<LoginRecord> getLoginRecords(String userId) {
        return loginRecordRepository.findLoginRecordsByUserId(userId);
    }

    public void recordLogin(String userId, String ipAddress) {
        loginRecordRepository.saveLoginRecord(userId, ipAddress);
    }
}
