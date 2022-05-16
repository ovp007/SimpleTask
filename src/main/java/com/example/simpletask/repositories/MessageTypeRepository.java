package com.example.simpletask.repositories;

import com.example.simpletask.models.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTypeRepository extends JpaRepository<MessageType, Long> {

  Boolean existsByType(String messageType);

  MessageType findMessageTypeByType(String messageType);
}
