package com.example.simpletask.models;

import javax.persistence.*;

@Entity
@Table(name = "country_codes")
public class CountryCode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, name = "country_code")
  private String countryCode;

  public CountryCode() {}

  public CountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Long getId() {
    return id;
  }

  public String getCountryCode() {
    return countryCode;
  }
}
