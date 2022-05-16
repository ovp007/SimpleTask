package com.example.simpletask.dtos;

public class ErrorResponseDto implements ResponseDto {

  public final String error;

  public ErrorResponseDto(String error) {
    this.error = error;
  }
}
