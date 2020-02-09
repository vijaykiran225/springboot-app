package com.test.app.testapp.repository;

import com.test.app.testapp.repository.dto.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionLogRepository extends JpaRepository<ActionLog,Integer> {

    List<ActionLog> findAllByUserId(String userId);
}
