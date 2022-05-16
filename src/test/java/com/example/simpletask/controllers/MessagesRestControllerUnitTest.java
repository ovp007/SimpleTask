package com.example.simpletask.controllers;

import com.example.simpletask.dtos.CounterResponseDto;
import com.example.simpletask.dtos.StatusResponseDto;
import com.example.simpletask.exceptions.*;
import com.example.simpletask.services.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessagesRestController.class)
class MessagesRestControllerUnitTest {

  @MockBean private MessageService messageService;

  @Autowired private MockMvc mockMvc;

  @Test
  void request_with_missing_message_type_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(InvalidMessageTypeException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"timestamp\": 1517645700,\n"
                        + "\"origin\": 34969000001,\n"
                        + "\"destination\": 34969000101,\n"
                        + "\"duration\": 120,\n"
                        + "\"status_code\": \"OK\",\n"
                        + "\"status_description\": \"OK\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Invalid message type")));
  }

  @Test
  void request_with_missing_timestamp_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(TimeStampIsMissingException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"CALL\",\n"
                        + "\"origin\": 34969000001,\n"
                        + "\"destination\": 34969000101,\n"
                        + "\"duration\": 120,\n"
                        + "\"status_code\": \"OK\",\n"
                        + "\"status_description\": \"OK\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Timestamp is missing")));
  }

  @Test
  void request_with_missing_origin_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(MessageOriginIsMissingException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"CALL\",\n"
                        + "\"timestamp\": 1517645700,\n"
                        + "\"destination\": 34969000101,\n"
                        + "\"duration\": 120,\n"
                        + "\"status_code\": \"OK\",\n"
                        + "\"status_description\": \"OK\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Message origin is missing")));
  }

  @Test
  void request_with_missing_destination_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(MessageDestinationIsMissingException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"CALL\",\n"
                        + "\"timestamp\": 1517645700,\n"
                        + "\"origin\": 34969000001,\n"
                        + "\"duration\": 120,\n"
                        + "\"status_code\": \"OK\",\n"
                        + "\"status_description\": \"OK\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Message destination is missing")));
  }

  @Test
  void request_with_missing_duration_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(DurationIsMissingException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"CALL\",\n"
                        + "\"timestamp\": 1517645700,\n"
                        + "\"origin\": 34969000001,\n"
                        + "\"destination\": 34969000101,\n"
                        + "\"status_code\": \"OK\",\n"
                        + "\"status_description\": \"OK\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Duration is missing")));
  }

  @Test
  void request_with_missing_status_code_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(InvalidStatusCodeException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"CALL\",\n"
                        + "\"timestamp\": 1517645700,\n"
                        + "\"origin\": 34969000001,\n"
                        + "\"destination\": 34969000101,\n"
                        + "\"duration\": 120,\n"
                        + "\"status_description\": \"OK\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Invalid status code")));
  }

  @Test
  void request_with_missing_status_description_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(StatusDescriptionIsMissingException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"CALL\",\n"
                        + "\"timestamp\": 1517645700,\n"
                        + "\"origin\": 34969000001,\n"
                        + "\"destination\": 34969000101,\n"
                        + "\"duration\": 120,\n"
                        + "\"status_code\": \"OK\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Status description is missing")));
  }

  @Test
  void request_with_missing_message_content_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(MessageContentIsMissingException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"MSG\",\n"
                        + "\"timestamp\": 1517559332,\n"
                        + "\"origin\": 34960000003,\n"
                        + "\"destination\": 34960000103,\n"
                        + "\"message_status\": \"SEEN\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Message content is missing")));
  }

  @Test
  void request_with_missing_message_status_throw_error() throws Exception {

    Mockito.when(messageService.receiveMessage(Mockito.any()))
        .thenThrow(InvalidMessageStatusException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"MSG\",\n"
                        + "\"timestamp\": 1517559332,\n"
                        + "\"origin\": 34960000003,\n"
                        + "\"destination\": 34960000103,\n"
                        + "\"message_content\": \"B\"\n"
                        + "}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Invalid message status")));
  }

  @Test
  void messages_end_point() throws Exception {

    StatusResponseDto statusResponseDto = new StatusResponseDto("Message successfully saved");

    Mockito.when(messageService.receiveMessage(Mockito.any())).thenReturn(statusResponseDto);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "\"message_type\": \"CALL\",\n"
                        + "\"timestamp\": 1517645700,\n"
                        + "\"origin\": 34969000001,\n"
                        + "\"destination\": 34969000101,\n"
                        + "\"duration\": 120,\n"
                        + "\"status_code\": \"OK\",\n"
                        + "\"status_description\": \"OK\"\n"
                        + "}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.status", is("Message successfully saved")));
  }

  @Test
  void metrics_end_point() throws Exception {

    CounterResponseDto counterResponseDto = new CounterResponseDto(5L, 4L, 2L, 2L, 2L, 1L);
    Mockito.when(messageService.getCounters()).thenReturn(counterResponseDto);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/metrics"))
        .andExpect(status().isOk());

  }

  @Test
  void metrics_with_date_end_point() throws Exception {

    CounterResponseDto counterResponseDto = new CounterResponseDto(5L, 4L, 2L, 2L, 2L, 1L);
    Mockito.when(messageService.getCounters(Mockito.any())).thenReturn(counterResponseDto);

    mockMvc
            .perform(
                    MockMvcRequestBuilders.get("/metrics"))
            .andExpect(status().isOk());

  }
}
