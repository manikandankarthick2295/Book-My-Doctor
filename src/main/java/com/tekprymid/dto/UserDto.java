package com.tekprymid.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private int userId;
    private String name;
    private String gender;
    private long phone;
    private String email;
    private String password;
    private LocalDate DOB;


}

