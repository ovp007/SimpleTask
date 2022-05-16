package com.example.simpletask.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "message_types")
public class MessageType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;

  public MessageType() {}

  public MessageType(String type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public String getType() {
    return type;
  }
}
