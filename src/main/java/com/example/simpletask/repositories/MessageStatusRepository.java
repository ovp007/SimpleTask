package com.example.simpletask.repositories;

import com.example.simpletask.models.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageStatusRepository extends JpaRepository<MessageStatus, Long> {

  Boolean existsMessageStatusByMessageStatus(String messageStatus);

  MessageStatus findMessageStatusesByMessageStatus(String messageStatus);
}
