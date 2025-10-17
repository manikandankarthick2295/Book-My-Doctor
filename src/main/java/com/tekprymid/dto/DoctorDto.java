package com.tekprymid.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorDto {
    private int doctorId;
    private String name;
    private String spec;
    private long phone;
    private double rating;
    private LocalDate DOB;
    private String email;
}