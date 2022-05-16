package com.example.simpletask.services;

import com.example.simpletask.dtos.MessageRequestDto;
import com.example.simpletask.dtos.StatusResponseDto;
import com.example.simpletask.exceptions.*;
import com.example.simpletask.models.CountryCode;
import com.example.simpletask.models.MessageType;
import com.example.simpletask.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DatabaseMessageServiceUnitTest {

  private MessageRepository messageRepository;
  private MessageTypeRepository messageTypeRepository;
  private StatusCodeRepository statusCodeRepository;
  private MessageStatusRepository messageStatusRepository;
  private CountryCodeRepository countryCodeRepository;
  private MessageLogRepository messageLogRepository;
  private MessageService messageService;
  private Environment environment;
  private MessageType messageType;
  private Timestamp timestamp;
  private Long origin;
  private Long destination;
  private Long duration;
  private String statusCode;
  private String statusDescription;
  private String messageContent;
  private String messageStatus;

  @BeforeEach()
  void setUp() {
    this.messageRepository = Mockito.mock(MessageRepository.class);
    this.messageTypeRepository = Mockito.mock(MessageTypeRepository.class);
    this.statusCodeRepository = Mockito.mock(StatusCodeRepository.class);
    this.messageStatusRepository = Mockito.mock(MessageStatusRepository.class);
    this.countryCodeRepository = Mockito.mock(CountryCodeRepository.class);
    this.messageLogRepository = Mockito.mock(MessageLogRepository.class);
    this.environment = Mockito.mock(Environment.class);
    this.messageService =
        new DatabaseMessageService(
            messageRepository,
            messageTypeRepository,
            statusCodeRepository,
            messageStatusRepository,
            countryCodeRepository,
            messageLogRepository,
            environment);

    timestamp = new Timestamp(System.currentTimeMillis());
    origin = 34969000001L;
    destination = 34969000101L;
    duration = 120L;
    statusCode = "OK";
    statusDescription = "OK";
    messageContent = "B";
    this.messageStatus = "SEEN";
  }

  @Test
  void message_request_with_invalid_message_type_throw_exception() {

    messageType = new MessageType("msg");

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            origin,
            destination,
            duration,
            statusCode,
            statusDescription,
            null,
            null);

    assertThrows(
        InvalidMessageTypeException.class, () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void message_request_with_missing_timestamp_throw_exception() {

    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);

    messageType = new MessageType("MSG");
    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            null,
            origin,
            destination,
            duration,
            statusCode,
            statusDescription,
            null,
            null);

    assertThrows(
        TimeStampIsMissingException.class, () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void message_request_with_missing_origin_throw_exception() {

    messageType = new MessageType("MSG");

    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            null,
            destination,
            duration,
            statusCode,
            statusDescription,
            null,
            null);

    assertThrows(
        MessageOriginIsMissingException.class,
        () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void message_request_with_invalid_origin_msisdn_throw_exception() {

    messageType = new MessageType("MSG");

    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            origin,
            destination,
            duration,
            statusCode,
            statusDescription,
            null,
            null);

    assertThrows(
        InvalidMsisdnException.class, () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void message_request_with_missing_destination_throw_exception() {

    messageType = new MessageType("MSG");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
        .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
        .thenReturn(Optional.of(countryCodeOptional));

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            origin,
            null,
            duration,
            statusCode,
            statusDescription,
            null,
            null);

    assertThrows(
        MessageDestinationIsMissingException.class,
        () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void message_request_with_invalid_message_status_code_throw_exception() {

    messageType = new MessageType("MSG");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
        .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
        .thenReturn(Optional.of(countryCodeOptional));

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            origin,
            destination,
            duration,
            statusCode,
            statusDescription,
            messageContent,
            messageStatus);

    assertThrows(
        InvalidMessageStatusException.class,
        () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void message_request_with_duration_throw_exception2() {

    messageType = new MessageType("MSG");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
        .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
        .thenReturn(Optional.of(countryCodeOptional));
    Mockito.when(messageStatusRepository.existsMessageStatusByMessageStatus(Mockito.any()))
        .thenReturn(true);

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            origin,
            destination,
            duration,
            null,
            null,
            messageContent,
            messageStatus);

    assertThrows(
        IncorrectDataForMessageRequest.class,
        () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void message_request_with_status_code_throw_exception() {

    messageType = new MessageType("MSG");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
        .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
        .thenReturn(Optional.of(countryCodeOptional));
    Mockito.when(messageStatusRepository.existsMessageStatusByMessageStatus(Mockito.any()))
        .thenReturn(true);

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            origin,
            destination,
            null,
            statusCode,
            null,
            messageContent,
            messageStatus);

    assertThrows(
        IncorrectDataForMessageRequest.class,
        () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void message_request_with_status_description_throw_exception() {

    messageType = new MessageType("MSG");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
        .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
        .thenReturn(Optional.of(countryCodeOptional));
    Mockito.when(messageStatusRepository.existsMessageStatusByMessageStatus(Mockito.any()))
        .thenReturn(true);

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            origin,
            destination,
            null,
            null,
            statusDescription,
            messageContent,
            messageStatus);

    assertThrows(
        IncorrectDataForMessageRequest.class,
        () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void correct_message_request() {

    messageType = new MessageType("MSG");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
        .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
        .thenReturn(Optional.of(countryCodeOptional));
    Mockito.when(messageStatusRepository.existsMessageStatusByMessageStatus(Mockito.any()))
        .thenReturn(true);
    Mockito.when(environment.getProperty("simple.task.config.message.success")).thenReturn("Message saved successfully");

    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            messageType.getType(),
            timestamp,
            origin,
            destination,
            null,
            null,
            null,
            messageContent,
            messageStatus);

    StatusResponseDto responseDto = messageService.receiveMessage(messageRequestDto);

    assertEquals("Message saved successfully", responseDto.status);
  }

  @Test
  void call_message_with_invalid_status_code_throw_exception() {

    messageType = new MessageType("CALL");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
            .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
            .thenReturn(Optional.of(countryCodeOptional));

    MessageRequestDto messageRequestDto =
            new MessageRequestDto(
                    messageType.getType(),
                    timestamp,
                    origin,
                    destination,
                    duration,
                    statusCode,
                    statusDescription,
                    null,
                    null);

    assertThrows(
            InvalidStatusCodeException.class,
            () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void call_message_with_message_content_throw_exception() {

    messageType = new MessageType("CALL");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
            .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
            .thenReturn(Optional.of(countryCodeOptional));
    Mockito.when(statusCodeRepository.existsByStatusCode(Mockito.any()))
            .thenReturn(true);


    MessageRequestDto messageRequestDto =
            new MessageRequestDto(
                    messageType.getType(),
                    timestamp,
                    origin,
                    destination,
                    duration,
                    statusCode,
                    statusDescription,
                    messageContent,
                    null);

    assertThrows(
            ContentOrStatusFilledForCallRequestException.class,
            () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void call_message_with_message_status_throw_exception() {

    messageType = new MessageType("CALL");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
            .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
            .thenReturn(Optional.of(countryCodeOptional));
    Mockito.when(statusCodeRepository.existsByStatusCode(Mockito.any()))
            .thenReturn(true);


    MessageRequestDto messageRequestDto =
            new MessageRequestDto(
                    messageType.getType(),
                    timestamp,
                    origin,
                    destination,
                    duration,
                    statusCode,
                    statusDescription,
                    null,
                    messageStatus);

    assertThrows(
            ContentOrStatusFilledForCallRequestException.class,
            () -> messageService.receiveMessage(messageRequestDto));
  }

  @Test
  void correct_call_message() {

    messageType = new MessageType("CALL");

    CountryCode countryCodeOptional = new CountryCode("39");
    Mockito.when(messageTypeRepository.existsByType(Mockito.any())).thenReturn(true);
    Mockito.when(countryCodeRepository.existsCountryCodeByCountryCode((Mockito.any())))
            .thenReturn(true);
    Mockito.when(countryCodeRepository.findCountryCodeByCountryCodeIn(Mockito.any()))
            .thenReturn(Optional.of(countryCodeOptional));
    Mockito.when(statusCodeRepository.existsByStatusCode(Mockito.any()))
            .thenReturn(true);
    Mockito.when(environment.getProperty("simple.task.config.message.success")).thenReturn("Message saved successfully");


    MessageRequestDto messageRequestDto =
            new MessageRequestDto(
                    messageType.getType(),
                    timestamp,
                    origin,
                    destination,
                    duration,
                    statusCode,
                    statusDescription,
                    null,
                    null);

    StatusResponseDto responseDto = messageService.receiveMessage(messageRequestDto);

    assertEquals("Message saved successfully", responseDto.status);
  }

}
