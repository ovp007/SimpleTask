package com.example.simpletask.services;

import com.example.simpletask.dtos.CounterResponseDto;
import com.example.simpletask.dtos.MessageRequestDto;
import com.example.simpletask.dtos.StatusResponseDto;

import java.text.ParseException;


public interface MessageService {

    StatusResponseDto receiveMessage(MessageRequestDto messageRequestDto);

    CounterResponseDto getCounters();

    CounterResponseDto getCounters(String date) throws ParseException;
}
