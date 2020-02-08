package com.test.app.testapp.repository.dto;

import com.test.app.testapp.model.request.RegistrationRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RegisteredUser {
    @Id
    @GeneratedValue
    Integer id;
    String userName;
    String password;
    String email;
    String token;

    public RegisteredUser() {
    }

    public RegisteredUser(String userName, String password, String email, String token) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
