package com.example.simpletask.models;

import javax.persistence.*;

@Entity(name = "message_statuses")
public class MessageStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "message_status")
  private String messageStatus;

  public MessageStatus() {}

  public MessageStatus(String messageStatus) {
    this.messageStatus = messageStatus;
  }

  public Long getId() {
    return id;
  }

  public String getMessageStatus() {
    return messageStatus;
  }
}
