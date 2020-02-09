package com.test.app.testapp.utils;

import com.test.app.testapp.model.request.TaskRequest;
import com.test.app.testapp.model.response.LogData;
import com.test.app.testapp.model.response.LogResponse;
import com.test.app.testapp.model.response.TaskResponse;
import com.test.app.testapp.repository.dto.ActionLog;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static LogResponse mapToResponse(List<ActionLog> logs) {
        LogResponse logResponse = new LogResponse();
        List<LogData> logDataList =logs.stream().map(actionLog -> {
           LogData data=new LogData();

           data.setRequest(
                   new TaskRequest(
                           actionLog.getOperation(),
                           Arrays.asList(actionLog.getRequest().split(",")
                           )
                   )
           );
           data.setResponse(new TaskResponse(actionLog.getResponse()));
           data.setTime(new Date(actionLog.getTime()));
           return data;
        }).collect(Collectors.toList());

        logResponse.setLogs(logDataList);
        return logResponse;
    }
}
