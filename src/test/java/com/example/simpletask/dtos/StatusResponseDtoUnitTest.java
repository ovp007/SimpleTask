package com.example.simpletask.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusResponseDtoUnitTest {

    @Test
    void can_create_status_response_dto(){

        StatusResponseDto statusResponseDto = new StatusResponseDto("ok");

        assertEquals("ok", statusResponseDto.status);

    }

}