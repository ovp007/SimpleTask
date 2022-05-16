package com.example.simpletask.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageUnitTest {

    @Test
    void can_create_message(){

        Message message = new Message(new MessageType("MSG"), new Date(),34969000001L, 34969000001L,120L, new StatusCode("OK"), "OK", new CountryCode("34"),new CountryCode("34") );

        assertEquals("MSG", message.getMessageType().getType());
        assertEquals(34969000001L, message.getOrigin());
        assertEquals(34969000001L, message.getDestination());
        assertEquals("OK", message.getStatusCode().getStatusCode());
        assertEquals("34", message.getOriginCountry().getCountryCode());
        assertEquals("OK", message.getDestinationCountry().getCountryCode());
        assertEquals(120L, message.getDuration());
    }

}