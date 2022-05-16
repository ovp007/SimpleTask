package com.example.simpletask.repositories;

import com.example.simpletask.models.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface CountryCodeRepository extends JpaRepository<CountryCode, Long> {

  Boolean existsCountryCodeByCountryCode(String countryCode);

  Optional<CountryCode> findCountryCodeByCountryCodeIn(Collection<String> countryCode);
}
