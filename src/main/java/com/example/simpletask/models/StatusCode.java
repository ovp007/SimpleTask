package com.example.simpletask.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name = "status_codes")
public class StatusCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_code")
    private String statusCode;

    public StatusCode() {
    }

    public StatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Long getId() {
        return id;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
