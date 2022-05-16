package com.example.simpletask.advice;

import com.example.simpletask.dtos.ErrorResponseDto;
import com.example.simpletask.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  private final Environment environment;

  @Autowired
  public ControllerExceptionHandler(Environment environment) {
    this.environment = environment;
  }

  @ExceptionHandler(InvalidRequestException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto invalidRequestException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.invalid.request"));
  }

  @ExceptionHandler(InvalidMsisdnException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto invalidMsisdnException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.msisdn.is.invalid"));
  }

  @ExceptionHandler(DurationIsMissingException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto durationIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.duration.is.missing"));
  }

  @ExceptionHandler(MessageContentIsMissingException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto messageContentIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.message.content.is.missing"));
  }

  @ExceptionHandler(MessageDestinationIsMissingException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto messageDestinationIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.message.destination.is.missing"));
  }

  @ExceptionHandler(MessageOriginIsMissingException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto messageOriginIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.message.origin.is.missing"));
  }

  @ExceptionHandler(InvalidMessageStatusException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto messageStatusIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.message.status.is.missing"));
  }

  @ExceptionHandler(InvalidMessageTypeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto messageTypeIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.message.type.is.missing"));
  }

  @ExceptionHandler(InvalidStatusCodeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto statusCodeIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.status.code.is.missing"));
  }

  @ExceptionHandler(StatusDescriptionIsMissingException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto statusDescriptionIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.status.description.is.missing"));
  }

  @ExceptionHandler(TimeStampIsMissingException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto timeStampIsMissingException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.timestamp.is.missing"));
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto httpMessageNotReadableException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.reading.request"));
  }

  @ExceptionHandler({ContentOrStatusFilledForCallRequestException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto contentOrStatusFilledForCallRequestException() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.content.or.status.filled"));
  }

  @ExceptionHandler({IncorrectDataForMessageRequest.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto incorrectDataForMessageRequest() {
    return new ErrorResponseDto(
        environment.getProperty("simple.task.config.errors.incorrect.data.for.call.request"));
  }

  @ExceptionHandler({UnknownErrorException.class, Exception.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponseDto unknownErrorException() {
    return new ErrorResponseDto(environment.getProperty("simple.task.config.errors.unknown"));
  }
}
