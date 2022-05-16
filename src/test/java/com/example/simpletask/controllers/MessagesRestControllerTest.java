package com.example.simpletask.controllers;

import com.example.simpletask.repositories.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MessagesRestControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private MessageRepository messageRepository;

  @Test
  void correct_message_request() throws Exception {

    assertEquals(0, messageRepository.count());

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
                        + "\"message_content\": \"B\",\n"
                        + "\"message_status\": \"SEEN\"\n"
                        + "}"))
        .andExpect(status().isCreated());

    assertEquals(1, messageRepository.count());
  }
}
