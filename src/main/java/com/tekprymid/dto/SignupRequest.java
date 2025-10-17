package com.tekprymid.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private long phone;
    private String specialist;
    private String gender;
    private LocalDate date;
    private double rating;
}
