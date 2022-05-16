package com.example.simpletask.repositories;

import com.example.simpletask.models.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusCodeRepository extends JpaRepository<StatusCode, Long> {

  Boolean existsByStatusCode(String statusCode);

  StatusCode findStatusCodeByStatusCode(String statusCode);
}
