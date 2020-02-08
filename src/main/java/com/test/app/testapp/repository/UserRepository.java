package com.test.app.testapp.repository;

import com.test.app.testapp.repository.dto.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<RegisteredUser,Integer> {

    Optional<RegisteredUser> findByUserNameAndPassword(String userName, String password);

    Optional<RegisteredUser> findByUserName(String userName);
}
