package com.tekprymid.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequest {
    private int doctorId;
    private int userId;
    private LocalDate appDate;
    private LocalTime appTime;
}

