package com.example.simpletask.models;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "message_logs")
public class MessageLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "message_counter")
  private Long messageCounter;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  public MessageLog() {}

  public MessageLog(Long messageCounter, @NonNull Date timestamp) {
    this.messageCounter = messageCounter;
    this.timestamp = timestamp;
  }

  public Long getMessageCounter() {
    return messageCounter;
  }
}
