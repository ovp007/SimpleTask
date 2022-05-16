package com.example.simpletask.util;

import com.example.simpletask.models.CountryCode;
import com.example.simpletask.models.MessageStatus;
import com.example.simpletask.models.MessageType;
import com.example.simpletask.models.StatusCode;
import com.example.simpletask.repositories.CountryCodeRepository;
import com.example.simpletask.repositories.MessageStatusRepository;
import com.example.simpletask.repositories.MessageTypeRepository;
import com.example.simpletask.repositories.StatusCodeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final CountryCodeRepository countryCodeRepository;
  private final StatusCodeRepository statusCodeRepository;
  private final MessageStatusRepository messageStatusRepository;
  private final MessageTypeRepository messageTypeRepository;


  public DataLoader(
      CountryCodeRepository countryCodeRepository,
      StatusCodeRepository statusCodeRepository,
      MessageStatusRepository messageStatusRepository,
      MessageTypeRepository messageTypeRepository) {
    this.countryCodeRepository = countryCodeRepository;
    this.statusCodeRepository = statusCodeRepository;
    this.messageStatusRepository = messageStatusRepository;
    this.messageTypeRepository = messageTypeRepository;
  }

  @Override
  public void run(String... args) {

      MessageType messageTypeCall = new MessageType("CALL");
      MessageType messageTypeMSG = new MessageType("MSG");
      messageTypeRepository.save(messageTypeCall);
      messageTypeRepository.save(messageTypeMSG);

      StatusCode statusCode1 = new StatusCode("OK");
      StatusCode statusCode2 = new StatusCode("KO");
      statusCodeRepository.save(statusCode1);
      statusCodeRepository.save(statusCode2);

      MessageStatus messageStatus1 = new MessageStatus("SEEN");
      MessageStatus messageStatus2 = new MessageStatus("DELIVERED");
      messageStatusRepository.save(messageStatus1);
      messageStatusRepository.save(messageStatus2);

      CountryCode countryCode1 = new CountryCode("34");
      CountryCode countryCode2 = new CountryCode("44");
      CountryCode countryCode3 = new CountryCode("25");
      CountryCode countryCode4 = new CountryCode("58");
      CountryCode countryCode5 = new CountryCode("49");
      CountryCode countryCode6 = new CountryCode("91");
      CountryCode countryCode7 = new CountryCode("410");

      countryCodeRepository.save(countryCode1);
      countryCodeRepository.save(countryCode2);
      countryCodeRepository.save(countryCode3);
      countryCodeRepository.save(countryCode4);
      countryCodeRepository.save(countryCode5);
      countryCodeRepository.save(countryCode6);
      countryCodeRepository.save(countryCode7);


  }
}
