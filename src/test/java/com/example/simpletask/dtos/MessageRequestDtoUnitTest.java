package com.example.simpletask.dtos;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageRequestDtoUnitTest {

  @Test
  void can_create_message_request_dto() {
    MessageRequestDto messageRequestDto =
        new MessageRequestDto(
            "MSG", new Date(), 34969000001L, 34969000001L, 120L, "OK", "OK", null, null);

    assertEquals("MSG", messageRequestDto.messageType);
    assertEquals(34969000001L, messageRequestDto.origin);
    assertEquals(34969000001L, messageRequestDto.destination);
    assertEquals(120L, messageRequestDto.duration);
    assertEquals("OK", messageRequestDto.statusCode);
    assertEquals("OK", messageRequestDto.statusDescription);
  }
}
