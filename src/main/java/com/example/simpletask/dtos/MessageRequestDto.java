package com.example.simpletask.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MessageRequestDto {

  @JsonProperty("message_type")
  public final String messageType;

  public final Date timestamp;

  public final Long origin;

  public final Long destination;

  public final Long duration;

  @JsonProperty("status_code")
  public final String statusCode;

  @JsonProperty("status_description")
  public final String statusDescription;

  @JsonProperty("message_content")
  public final String messageContent;

  @JsonProperty("message_status")
  public final String messageStatus;

  @JsonCreator
  public MessageRequestDto(
      String messageType,
      Date timestamp,
      Long origin,
      Long destination,
      Long duration,
      String statusCode,
      String statusDescription,
      String messageContent,
      String messageStatus) {
    this.messageType = messageType;
    this.timestamp = timestamp;
    this.origin = origin;
    this.destination = destination;
    this.duration = duration;
    this.statusCode = statusCode;
    this.statusDescription = statusDescription;
    this.messageContent = messageContent;
    this.messageStatus = messageStatus;
  }
}
