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
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;

public class AccessServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccessService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidUserException.class)
    public void logoutException() throws InvalidUserException {
        String token = "7c2c4c04-61de-499a-8226-16e08b1f91cb";
        service.logout(token);
        Mockito.when(sessionRepository.findByToken(token))
                .thenReturn(Optional.empty());
    }

    @Test
    public void logout() {
        try {
            String token = "7c2c4c04-61de-499a-8226-16e08b1f91cb";

            UserSession session = new UserSession();
            session.setToken(token);
            Mockito.when(sessionRepository.findByToken(token))
                    .thenReturn(Optional.of(session));
            Mockito.doNothing().when(sessionRepository).deleteByToken(session);

            boolean logout = service.logout(token);
            assertTrue(logout);
        } catch (Exception e) {
            fail("Exception not allowed here");
        }

    }

    @Test(expected = UserAlreadyExistsException.class)
    public void registerEx() throws UserAlreadyExistsException {
        String name = "abc";
        RegistrationRequest req=new RegistrationRequest();
        req.setUserName(name);
        Mockito.when(userRepository.findByUserName(name))
                .thenReturn(Optional.of(new RegisteredUser()));
        service.register(req);
    }

    @Test
    public void register() {
        try {
            String name = "abc";
            RegistrationRequest req=new RegistrationRequest();
            req.setUserName(name);

            Mockito.when(userRepository.findByUserName(name))
                    .thenReturn(Optional.empty());

            Mockito.when(userRepository.save(Mockito.any(RegisteredUser.class)))
                    .thenReturn(mockUser());

            boolean registered = service.register(req);
            assertTrue(registered);
        } catch (Exception e) {
            fail("Exception not allowed here");
        }

    }

    @Test(expected = InvalidUserException.class)
    public void loginEx() throws InvalidUserException {
        String name="sample";
        String pd="sample";
        Mockito.when(userRepository.findByUserNameAndPassword(name,pd))
                .thenReturn(Optional.empty());
        LoginRequest req=new LoginRequest(name,pd);
        service.login(req);
    }
    @Test
    public void login() {
        try {
            String name="sample";
            String pd="sample";
            String token = "7c2c4c04-61de-499a-8226-16e08b1f91cb";

            UserSession session = new UserSession();
            session.setToken(token);
            Mockito.when(userRepository.findByUserNameAndPassword(name,pd))
                    .thenReturn(Optional.of(mockUser()));
            Mockito.when(
                    sessionRepository.save(Mockito.any(UserSession.class))
            ).thenReturn(session);
            LoginRequest req=new LoginRequest(name,pd);
            LoginResponse resp = service.login(req);
            assertEquals(token,resp.getAccessToken());
        } catch (Exception e) {
            fail("Excpe not allowed here");
        }

    }

    @Test
    public void loginIdempotent() {
        try {
            String name="sample";
            String pd="sample";
            String token = "7c2c4c04-61de-499a-8226-16e08b1f91cb";

            UserSession session = new UserSession();
            session.setToken(token);
            Mockito.when(userRepository.findByUserNameAndPassword(name,pd))
                    .thenReturn(Optional.of(mockUser()));
            Mockito.when(
                    sessionRepository.save(Mockito.any(UserSession.class))
            ).thenReturn(session);

            Mockito.when(sessionRepository.findByUserName(name)).thenReturn(Optional.of(token));

            LoginRequest req=new LoginRequest(name,pd);
            LoginResponse resp = service.login(req);
            assertEquals(token,resp.getAccessToken());
        } catch (Exception e) {
            fail("Excpe not allowed here");
        }

    }

    private RegisteredUser mockUser() {
        RegisteredUser mockUser = new RegisteredUser();
        return mockUser;
    }
}