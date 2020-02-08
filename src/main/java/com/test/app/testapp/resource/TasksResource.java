package com.test.app.testapp.resource;

import com.test.app.testapp.model.request.TaskRequest;
import com.test.app.testapp.model.response.ErrorResponse;
import com.test.app.testapp.model.response.LogResponse;
import com.test.app.testapp.model.response.TaskResponse;
import com.test.app.testapp.repository.SessionRepository;
import com.test.app.testapp.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController
@RequestMapping("/tasks")
public class TasksResource {

    @Autowired
    private OperationService service;

    @Autowired
    private SessionRepository session;

    @PostMapping("/execute")
    public ResponseEntity execute(@RequestBody TaskRequest request,
                                  @RequestHeader("access-token") String token){
        if(!StringUtils.isEmpty(token)
                && session.findById(Integer.valueOf(token)).isPresent() ){
            try {
                TaskResponse response = service.execute(request);
                return new ResponseEntity(response, HttpStatus.OK);
            }catch (NotImplementedException e){
                return new ResponseEntity(new ErrorResponse("UNKNOWN_OPERATION"), HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity(new ErrorResponse("INTERNAL_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(new ErrorResponse("INVALID_TOKEN"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/log")
    public LogResponse log(@RequestHeader("access-token") String token){
        return new LogResponse();
    }
}
