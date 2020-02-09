package com.test.app.testapp.service;

import com.test.app.testapp.model.request.TaskRequest;
import com.test.app.testapp.repository.ActionLogRepository;
import com.test.app.testapp.repository.dto.ActionLog;
import com.test.app.testapp.repository.dto.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ActionLogService {

    @Autowired
    private ActionLogRepository logRepository;

    public boolean log(TaskRequest request, long sum, UserSession session) {

        ActionLog logData=new ActionLog();
        logData.setOperation(request.getOperationType());
        logData.setResponse(Long.toString(sum));
        logData.setTime(System.currentTimeMillis());
        logData.setRequest(String.join(",", request.getOperands()));
        logData.setUserId(String.valueOf(session.getId()));
        try {
            logRepository.save(logData);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<ActionLog> fetch(Integer userId) {
        try {
            List<ActionLog> logs = logRepository.findAllByUserId(String.valueOf(userId));
            return logs;
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}

