package com.test.app.testapp.repository;

import com.test.app.testapp.repository.dto.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionRepository {

    @Autowired
    private RedisTemplate redisTemplate;


    public Optional<UserSession> findById(int id) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        UserSession x = (UserSession) hashOperations.get("user", id);
        return Optional.ofNullable(x);
    }

    public void deleteById(Integer id) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete("user", id);
    }

    public UserSession save(UserSession session) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("user", session.getId(), session);
        return session;
    }
}
