package com.test.app.testapp.service;

import com.test.app.testapp.model.request.TaskRequest;
import com.test.app.testapp.model.response.TaskResponse;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class OperationService {

    public TaskResponse execute(TaskRequest request){

        if(request.getOperationType().equals("ADD")){
            long sum = request
                    .getOperands()
                    .stream()
                    .mapToLong(Long::valueOf)
                    .sum();
            return new TaskResponse(Long.toString(sum));
        }else {
            throw new NotImplementedException();
        }
    }
}
