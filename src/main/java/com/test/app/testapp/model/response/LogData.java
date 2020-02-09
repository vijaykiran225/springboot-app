package com.test.app.testapp.model.response;

import com.test.app.testapp.model.request.TaskRequest;

import java.util.Date;

public class LogData {
    private TaskResponse response;
    private TaskRequest request;
    private Date time;

    public TaskResponse getResponse() {
        return response;
    }

    public void setResponse(TaskResponse response) {
        this.response = response;
    }

    public TaskRequest getRequest() {
        return request;
    }

    public void setRequest(TaskRequest request) {
        this.request = request;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
