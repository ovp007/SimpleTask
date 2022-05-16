package com.example.simpletask.services;

import com.example.simpletask.dtos.CounterResponseDto;
import com.example.simpletask.dtos.MessageRequestDto;
import com.example.simpletask.dtos.StatusResponseDto;
import com.example.simpletask.exceptions.*;
import com.example.simpletask.models.CountryCode;
import com.example.simpletask.models.Message;
import com.example.simpletask.models.MessageLog;
import com.example.simpletask.repositories.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DatabaseMessageService implements MessageService {

  private final MessageRepository messageRepository;
  private final MessageTypeRepository messageTypeRepository;
  private final StatusCodeRepository statusCodeRepository;
  private final MessageStatusRepository messageStatusRepository;
  private final CountryCodeRepository countryCodeRepository;
  private final MessageLogRepository messageLogRepository;
  private final Environment environment;

  public DatabaseMessageService(
      MessageRepository messageRepository,
      MessageTypeRepository messageTypeRepository,
      StatusCodeRepository statusCodeRepository,
      MessageStatusRepository messageStatusRepository,
      CountryCodeRepository countryCodeRepository,
      MessageLogRepository messageLogRepository,
      Environment environment) {
    this.messageRepository = messageRepository;
    this.messageTypeRepository = messageTypeRepository;
    this.statusCodeRepository = statusCodeRepository;
    this.messageStatusRepository = messageStatusRepository;
    this.countryCodeRepository = countryCodeRepository;
    this.messageLogRepository = messageLogRepository;
    this.environment = environment;
  }

  @Override
  public StatusResponseDto receiveMessage(MessageRequestDto messageRequestDto) {

    Message message = null;

    MessageLog log = getMessageLog(messageRequestDto);
    messageLogRepository.save(log);

    if (messageRequestDto == null) throw new InvalidRequestException();

    if (messageRequestDto.messageType == null
        || !messageTypeRepository.existsByType(messageRequestDto.messageType))
      throw new InvalidMessageTypeException();

    if (messageRequestDto.timestamp == null) throw new TimeStampIsMissingException();

    if (messageRequestDto.origin == null) throw new MessageOriginIsMissingException();

    CountryCode originCountry = getCountry(messageRequestDto.origin);

    if (messageRequestDto.destination == null) throw new MessageDestinationIsMissingException();

    CountryCode destinationCountry = getCountry(messageRequestDto.destination);

    if (messageRequestDto.messageType.equals("CALL")) {
      if (messageRequestDto.duration == null) throw new DurationIsMissingException();
      if (messageRequestDto.statusCode == null
          || !statusCodeRepository.existsByStatusCode(messageRequestDto.statusCode))
        throw new InvalidStatusCodeException();
      if (messageRequestDto.statusDescription == null)
        throw new StatusDescriptionIsMissingException();
      if (messageRequestDto.messageContent != null || messageRequestDto.messageStatus != null)
        throw new ContentOrStatusFilledForCallRequestException();

      message =
          new Message(
              messageTypeRepository.findMessageTypeByType(messageRequestDto.messageType),
              messageRequestDto.timestamp,
              messageRequestDto.origin,
              messageRequestDto.destination,
              messageRequestDto.duration,
              statusCodeRepository.findStatusCodeByStatusCode(messageRequestDto.statusCode),
              messageRequestDto.statusDescription,
              originCountry,
              destinationCountry);
    }

    if (messageRequestDto.messageType.equals("MSG")) {
      if (messageRequestDto.messageContent == null) throw new MessageContentIsMissingException();
      if (messageRequestDto.messageStatus == null
          || !messageStatusRepository.existsMessageStatusByMessageStatus(
              messageRequestDto.messageStatus)) throw new InvalidMessageStatusException();
      if (messageRequestDto.duration != null
          || messageRequestDto.statusCode != null
          || messageRequestDto.statusDescription != null)
        throw new IncorrectDataForMessageRequest();

      message =
          new Message(
              messageTypeRepository.findMessageTypeByType(messageRequestDto.messageType),
              messageRequestDto.timestamp,
              messageRequestDto.origin,
              messageRequestDto.destination,
              messageRequestDto.messageContent,
              messageStatusRepository.findMessageStatusesByMessageStatus(
                  messageRequestDto.messageStatus),
              originCountry,
              destinationCountry);
    }

    if (message == null) throw new UnknownErrorException();

    messageRepository.save(message);

    return new StatusResponseDto(environment.getProperty("simple.task.config.message.success"));
  }

  private MessageLog getMessageLog(MessageRequestDto messageRequestDto) {

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Long currentNumberOfMessagesProcessed = messageLogRepository.count();

    if (messageRequestDto == null || messageRequestDto.timestamp == null)
      return new MessageLog(currentNumberOfMessagesProcessed + 1, timestamp);
    else return new MessageLog(currentNumberOfMessagesProcessed + 1, messageRequestDto.timestamp);
  }

  private CountryCode getCountry(Long origin) {

    Collection<String> possibleCountryCodes = new ArrayList<>();
    possibleCountryCodes.add(String.valueOf(origin).substring(0, 3));
    possibleCountryCodes.add(String.valueOf(origin).substring(0, 2));
    possibleCountryCodes.add(String.valueOf(origin).substring(0, 1));

    Optional<CountryCode> countryCode =
        countryCodeRepository.findCountryCodeByCountryCodeIn(possibleCountryCodes);

    if (countryCode.isEmpty()) throw new InvalidMsisdnException();
    else return countryCode.get();
  }

  @Override
  public CounterResponseDto getCounters() {

    return new CounterResponseDto(
        getTotalJsonProcessed(),
        messageRepository.count(),
        messageRepository.countMessagesByMessageTypeType("CALL"),
        messageRepository.countMessagesByMessageTypeType("MSG"),
        messageRepository.findDistinctOriginCountries(),
        messageRepository.findDistinctDestinationCountries());
  }

  @Override
  public CounterResponseDto getCounters(String date) {

    String dateSelected =
        date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);

    List<Message> messages = messageRepository.findByTimestamp(dateSelected);

    return new CounterResponseDto(
        getTotalJsonProcessed(),
        (long) messages.size(),
        (messages.stream().filter(x -> x.getMessageType().getType().equals("MSG")).count()),
        (messages.stream().filter(x -> x.getMessageType().getType().equals("CALL")).count()),
        messageRepository.findDistinctOriginCountriesByDate(dateSelected),
        messageRepository.findDistinctDestinationCountriesByDate(dateSelected));
  }

  private Long getTotalJsonProcessed() {

    Optional<MessageLog> messageLogOptional = messageLogRepository.findFirstByOrderByIdDesc();

    if (messageLogOptional.isPresent()) return messageLogOptional.get().getMessageCounter();
    else return 0L;
  }
}
