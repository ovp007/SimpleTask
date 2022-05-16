package com.example.simpletask.repositories;

import com.example.simpletask.models.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {

  Optional<MessageLog> findFirstByOrderByIdDesc();
}
