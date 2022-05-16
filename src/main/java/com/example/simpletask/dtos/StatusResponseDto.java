package com.example.simpletask.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

public class StatusResponseDto implements ResponseDto {

  public final String status;

  @JsonCreator
  public StatusResponseDto(String status) {
    this.status = status;
  }
}
