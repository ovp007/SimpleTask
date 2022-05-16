package com.example.simpletask.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CounterResponseDto implements ResponseDto {

  @JsonProperty("number_of_processed_files")
  private Long numberOfProcessedFiles;

  @JsonProperty("number_of_rows")
  private Long numberOfRows;

  @JsonProperty("number_of_calls")
  private Long numberOfCalls;

  @JsonProperty("number_of_messages")
  private Long numberOfMessages;

  @JsonProperty("number_of_different_origin_country_codes")
  private Long numberOfUniqueCountyCodes;

  @JsonProperty("number_of_different_destination_country_codes")
  private Long getNumberOfUniqueDestinations;

  public CounterResponseDto(
      Long numberOfProcessedFiles,
      Long numberOfRows,
      Long numberOfCalls,
      Long numberOfMessages,
      Long numberOfUniqueCountyCodes,
      Long getNumberOfUniqueDestinations) {
    this.numberOfProcessedFiles = numberOfProcessedFiles;
    this.numberOfRows = numberOfRows;
    this.numberOfCalls = numberOfCalls;
    this.numberOfMessages = numberOfMessages;
    this.numberOfUniqueCountyCodes = numberOfUniqueCountyCodes;
    this.getNumberOfUniqueDestinations = getNumberOfUniqueDestinations;
  }
}
