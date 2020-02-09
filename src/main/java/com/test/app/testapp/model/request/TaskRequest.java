package com.test.app.testapp.model.request;

import java.util.ArrayList;
import java.util.List;

public class TaskRequest {
    private String operationType;
    private List<String> operands = new ArrayList<>();

    public TaskRequest(String operationType, List<String> operands) {
        this.operationType = operationType;
        this.operands = operands;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public List<String> getOperands() {
        return operands;
    }

    public void setOperands(List<String> operands) {
        this.operands = operands;
    }
}
