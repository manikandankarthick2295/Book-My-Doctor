package com.tekprymid.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDto {
    private int leaveRqtId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String status;
    private String reason;
    private int doctorId; // just reference ID to avoid nested object
}
