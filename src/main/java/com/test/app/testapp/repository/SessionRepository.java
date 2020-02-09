package com.test.app.testapp.repository;

import com.test.app.testapp.repository.dto.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
public class SessionRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    public Optional<UserSession> findByToken(String token) {
        if(!StringUtils.isEmpty(token)) {
            return Optional.empty();
        }
        HashOperations hashOperations = redisTemplate.opsForHash();
        UserSession session = (UserSession) hashOperations.get("user", token);
        return Optional.ofNullable(session);
    }

    public void deleteByToken(String token) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete("user", token);
    }

    public UserSession save(UserSession session) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("user", session.getToken(), session);
        return session;
    }
}
