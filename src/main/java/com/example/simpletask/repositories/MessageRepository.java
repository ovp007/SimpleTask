package com.example.simpletask.repositories;

import com.example.simpletask.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  Long countMessagesByMessageTypeType(String messageType);

  @Query(value = "select Count(distinct (origin_country_id)) from messages", nativeQuery = true)
  Long findDistinctOriginCountries();

  @Query(
      value = "select Count(distinct (destination_country_id)) from messages",
      nativeQuery = true)
  Long findDistinctDestinationCountries();

  @Query(
      value = "select Count(distinct (origin_country_id)) from messages where date(timestamp)= ?1",
      nativeQuery = true)
  Long findDistinctOriginCountriesByDate(String date);

  @Query(
      value =
          "select Count(distinct (destination_country_id)) from messages where date(timestamp)= ?1",
      nativeQuery = true)
  Long findDistinctDestinationCountriesByDate(String date);

  @Query(nativeQuery = true, value = "select * " + "from messages " + "where date(timestamp)= ?1 ")
  List<Message> findByTimestamp(String date);
}
