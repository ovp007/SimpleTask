package com.example.simpletask.controllers;

import com.example.simpletask.dtos.CounterResponseDto;
import com.example.simpletask.dtos.MessageRequestDto;
import com.example.simpletask.dtos.ResponseDto;
import com.example.simpletask.dtos.StatusResponseDto;
import com.example.simpletask.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class MessagesRestController {

  private final MessageService messageService;
  private final Logger logger = LoggerFactory.getLogger(MessagesRestController.class);

  @Autowired
  public MessagesRestController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping("/messages")
  public ResponseEntity<? extends ResponseDto> message(
      @RequestBody MessageRequestDto messageRequestDto) {

    StatusResponseDto statusResponseDto = messageService.receiveMessage(messageRequestDto);

    return new ResponseEntity<>(statusResponseDto, HttpStatus.CREATED);
  }

  @GetMapping("/metrics")
  public ResponseEntity<? extends ResponseDto> index() {

    CounterResponseDto counterDto = messageService.getCounters();

    return new ResponseEntity<>(counterDto, HttpStatus.OK);
  }

  @GetMapping("/metrics/{timestamp}")
  public ResponseEntity<? extends ResponseDto> show(@PathVariable String timestamp)
      throws ParseException {

    CounterResponseDto counterDto = messageService.getCounters(timestamp);

    return new ResponseEntity<>(counterDto, HttpStatus.OK);
  }
}
