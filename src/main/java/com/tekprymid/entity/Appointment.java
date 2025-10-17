package com.tekprymid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Appointment",
        uniqueConstraints = @UniqueConstraint(columnNames = {"doctor_id", "appointment_date", "appointment_time"}))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;



    @Column(name = "appointment_date")
    private LocalDate appDate;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "appointment_time")
    private LocalTime appTime;

    @ManyToOne
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "user_id")
    private User user;

}
