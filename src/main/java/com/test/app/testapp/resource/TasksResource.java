package com.test.app.testapp.resource;

import com.test.app.testapp.exceptions.InvalidDataException;
import com.test.app.testapp.model.request.TaskRequest;
import com.test.app.testapp.model.response.ErrorResponse;
import com.test.app.testapp.model.response.LogResponse;
import com.test.app.testapp.model.response.TaskResponse;
import com.test.app.testapp.repository.SessionRepository;
import com.test.app.testapp.repository.dto.UserSession;
import com.test.app.testapp.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksResource {

    @Autowired
    private OperationService service;

    @Autowired
    private SessionRepository session;

    @PostMapping("/execute")
    public ResponseEntity execute(@RequestBody TaskRequest request,
                                  @RequestHeader("access-token") String token) {

        Optional<UserSession> session = this.session.findByToken(token);
        if (session.isPresent()) {
            try {
                TaskResponse response = service.execute(request, session.get());
                return new ResponseEntity(response, HttpStatus.OK);
            } catch (NotImplementedException e) {
                return new ResponseEntity(new ErrorResponse("UNKNOWN_OPERATION"), HttpStatus.BAD_REQUEST);
            } catch (InvalidDataException e) {
                return new ResponseEntity(new ErrorResponse("INVALID_DATA"), HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity(new ErrorResponse("INTERNAL_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(new ErrorResponse("INVALID_TOKEN"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/history")
    public ResponseEntity history(@RequestHeader("access-token") String token) {
        Optional<UserSession> session = this.session.findByToken(token);
        if (session.isPresent()) {
            try {
                LogResponse response = service.fetchHistory(session.get());
                return new ResponseEntity(response, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(new ErrorResponse("INTERNAL_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(new ErrorResponse("INVALID_TOKEN"), HttpStatus.BAD_REQUEST);
    }
}
