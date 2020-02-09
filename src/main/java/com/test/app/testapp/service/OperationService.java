package com.test.app.testapp.service;

import com.test.app.testapp.exceptions.InvalidDataException;
import com.test.app.testapp.model.request.TaskRequest;
import com.test.app.testapp.model.response.LogResponse;
import com.test.app.testapp.model.response.TaskResponse;
import com.test.app.testapp.repository.dto.ActionLog;
import com.test.app.testapp.repository.dto.UserSession;
import com.test.app.testapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OperationService {

    @Autowired
    private ActionLogService logService;

    public TaskResponse execute(TaskRequest request, UserSession session) throws InvalidDataException {

        if (request == null
                || StringUtils.isEmpty(request.getOperationType())
                || CollectionUtils.isEmpty(request.getOperands())){
            throw new InvalidDataException();
        }

        if(request.getOperationType().equals("ADD")){
            long sum = request
                    .getOperands()
                    .stream()
                    .mapToLong(Long::valueOf)
                    .sum();
            boolean logged = logService.log(request, sum,session);
            if(!logged){
                Logger.getLogger("OperationService").log(Level.WARNING,"unable to log data");
            }
            return new TaskResponse(Long.toString(sum));
        }else {
            throw new NotImplementedException();
        }
    }

    public LogResponse fetchHistory(UserSession userSession) {
        List<ActionLog> logs = logService.fetch(userSession.getId());
        LogResponse logResponse = Utils.mapToResponse(logs);
        return logResponse;
    }
}
