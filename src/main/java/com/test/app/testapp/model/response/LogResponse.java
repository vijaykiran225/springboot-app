package com.test.app.testapp.model.response;

import com.test.app.testapp.model.LogData;

import java.util.ArrayList;
import java.util.List;

public class LogResponse {
    List<LogData> logs=new ArrayList<>();

    public List<LogData> getLogs() {
        return logs;
    }

    public void setLogs(List<LogData> logs) {
        this.logs = logs;
    }
}
