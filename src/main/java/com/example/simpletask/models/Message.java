package com.example.simpletask.models;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "messages")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull @ManyToOne private MessageType messageType;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  @NonNull private Long origin;

  @NonNull private Long destination;

  @Nullable private Long duration;

  @Nullable @ManyToOne private StatusCode statusCode;

  @Nullable
  @Column(name = "status_description")
  private String statusDescription;

  @Nullable
  @Column(name = "message_content")
  private String messageContent;

  @Nullable @ManyToOne private MessageStatus messageStatus;

  @NonNull @ManyToOne private CountryCode originCountry;

  @NonNull @ManyToOne private CountryCode destinationCountry;

  public Message() {}

  public Message(
      @NonNull MessageType messageType,
      @NonNull Date timestamp,
      @NonNull Long origin,
      @NonNull Long destination,
      @Nullable Long duration,
      @Nullable StatusCode statusCode,
      @Nullable String statusDescription,
      @Nullable String messageContent,
      @Nullable MessageStatus messageStatus,
      @NonNull CountryCode originCountry,
      @NonNull CountryCode destinationCountry) {
    this.messageType = messageType;
    this.timestamp = timestamp;
    this.origin = origin;
    this.destination = destination;
    this.duration = duration;
    this.statusCode = statusCode;
    this.statusDescription = statusDescription;
    this.messageContent = messageContent;
    this.messageStatus = messageStatus;
    this.originCountry = originCountry;
    this.destinationCountry = destinationCountry;
  }

  public Message(
      @NonNull MessageType messageType,
      @NonNull Date timestamp,
      @NonNull Long origin,
      @NonNull Long destination,
      @Nullable Long duration,
      @Nullable StatusCode statusCode,
      @Nullable String statusDescription,
      @NonNull CountryCode originCountry,
      @NonNull CountryCode destinationCountry) {
    this(
        messageType,
        timestamp,
        origin,
        destination,
        duration,
        statusCode,
        statusDescription,
        null,
        null,
        originCountry,
        destinationCountry);
  }

  public Message(
      @NonNull MessageType messageType,
      @NonNull Date timestamp,
      @NonNull Long origin,
      @NonNull Long destination,
      @Nullable String messageContent,
      @Nullable MessageStatus messageStatus,
      @NonNull CountryCode originCountry,
      @NonNull CountryCode destinationCountry) {
    this(
        messageType,
        timestamp,
        origin,
        destination,
        null,
        null,
        null,
        messageContent,
        messageStatus,
        originCountry,
        destinationCountry);
  }

  public Long getId() {
    return id;
  }

  @NonNull
  public MessageType getMessageType() {
    return messageType;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  @NonNull
  public Long getOrigin() {
    return origin;
  }

  @NonNull
  public Long getDestination() {
    return destination;
  }

  @Nullable
  public Long getDuration() {
    return duration;
  }

  @Nullable
  public StatusCode getStatusCode() {
    return statusCode;
  }

  @Nullable
  public String getStatusDescription() {
    return statusDescription;
  }

  @Nullable
  public String getMessageContent() {
    return messageContent;
  }

  @Nullable
  public MessageStatus getMessageStatus() {
    return messageStatus;
  }

  @NonNull
  public CountryCode getOriginCountry() {
    return originCountry;
  }

  @NonNull
  public CountryCode getDestinationCountry() {
    return destinationCountry;
  }
}
