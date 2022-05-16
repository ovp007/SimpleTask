package com.example.simpletask.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CounterResponseDto implements ResponseDto {

  @JsonProperty("number_of_processed_files")
  public final Long numberOfProcessedFiles;

  @JsonProperty("number_of_rows")
  public final Long numberOfRows;

  @JsonProperty("number_of_calls")
  public final Long numberOfCalls;

  @JsonProperty("number_of_messages")
  public final Long numberOfMessages;

  @JsonProperty("number_of_different_origin_country_codes")
  public final Long numberOfUniqueCountyCodes;

  @JsonProperty("number_of_different_destination_country_codes")
  public final Long getNumberOfUniqueDestinations;

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
