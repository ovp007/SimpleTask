package com.example.simpletask.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseDtoUnitTest {

    @Test
    void can_create_error_response_dto(){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto("some error");

        assertEquals("some error", errorResponseDto.error);
    }

}