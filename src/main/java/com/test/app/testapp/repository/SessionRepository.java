package com.test.app.testapp.repository;

import com.test.app.testapp.repository.dto.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<UserSession,Integer> {

    public Optional<UserSession> findByToken(String token);
}
