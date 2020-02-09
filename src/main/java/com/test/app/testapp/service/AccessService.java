package com.test.app.testapp.service;

import com.test.app.testapp.exceptions.InvalidUserException;
import com.test.app.testapp.exceptions.UserAlreadyExistsException;
import com.test.app.testapp.model.request.LoginRequest;
import com.test.app.testapp.model.request.RegistrationRequest;
import com.test.app.testapp.model.response.LoginResponse;
import com.test.app.testapp.repository.SessionRepository;
import com.test.app.testapp.repository.UserRepository;
import com.test.app.testapp.repository.dto.RegisteredUser;
import com.test.app.testapp.repository.dto.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class AccessService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean logout(String token) throws InvalidUserException {
        Optional<UserSession> existingSession = sessionRepository.findByToken(token);
        if(existingSession.isPresent()){
            sessionRepository.deleteByToken(existingSession.get().getToken());
            return true;
        }else {
            throw new InvalidUserException();
        }
    }

    public boolean register(RegistrationRequest request) throws UserAlreadyExistsException {
        if(userRepository.findByUserName(request.getUserName()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        RegisteredUser x=new RegisteredUser(request.getUserName(),request.getPassword(),request.getEmail(),null);
        RegisteredUser u=userRepository.save(x);
        return u!=null;
    }

    public LoginResponse login(LoginRequest request) throws InvalidUserException {
        Optional<RegisteredUser> existingUser = userRepository.findByUserNameAndPassword(
                request.getUserName(),
                request.getPassword());
        if(!existingUser.isPresent()){
            throw new InvalidUserException();
        }else {
            UserSession session=new UserSession();
            session.setUserName(existingUser.get().getUserName());
            session.setToken(UUID.randomUUID().toString());
            session.setId(existingUser.get().getId());
            UserSession val = sessionRepository.save(session);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(val.getToken());
            return loginResponse;
        }
    }
}