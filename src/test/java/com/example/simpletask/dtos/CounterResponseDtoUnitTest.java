package com.example.simpletask.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterResponseDtoUnitTest {

  @Test
  void can_create_counter_response_dto() {

    CounterResponseDto counterResponseDto = new CounterResponseDto(10L, 10L, 5L, 5L, 2L, 2L);

    assertEquals(10L, counterResponseDto.numberOfProcessedFiles);
    assertEquals(10L, counterResponseDto.numberOfRows);
    assertEquals(5L, counterResponseDto.numberOfCalls);
    assertEquals(5L, counterResponseDto.numberOfMessages);
    assertEquals(2L, counterResponseDto.numberOfUniqueCountyCodes);
    assertEquals(2L, counterResponseDto.getNumberOfUniqueDestinations);
  }
}
