package com.test.app.testapp.resource;

import com.test.app.testapp.exceptions.InvalidUserException;
import com.test.app.testapp.exceptions.UserAlreadyExistsException;
import com.test.app.testapp.model.request.LoginRequest;
import com.test.app.testapp.model.request.RegistrationRequest;
import com.test.app.testapp.model.response.ErrorResponse;
import com.test.app.testapp.model.response.LoginResponse;
import com.test.app.testapp.model.response.MessageResponse;
import com.test.app.testapp.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AccessResource {

    @Autowired
    private AccessService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request) {

        try {

            LoginResponse response = null;
            response = service.login(request);
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (InvalidUserException e) {
            return new ResponseEntity(new ErrorResponse("INVALID_USER"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("access-token") String token) {

        try {

            boolean loggedOut = service.logout(token);
            if (loggedOut) {
                return new ResponseEntity(new MessageResponse("LOGGED_OUT"), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ErrorResponse("INTERNAL_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (InvalidUserException e) {
            return new ResponseEntity(new ErrorResponse("INVALID_TOKEN"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationRequest request) {
        try {

            boolean registered = service.register(request);
            if (registered) {
                return new ResponseEntity(new MessageResponse("SUCCESS"), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ErrorResponse("INTERNAL_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
            }


        }catch (UserAlreadyExistsException e){
            return new ResponseEntity(new ErrorResponse("USER_ALREADY_EXISTS"), HttpStatus.BAD_REQUEST);
        }
    }
}
