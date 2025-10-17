package com.tekprymid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_request_id")
    private int leaveRqtId;

    private LocalDate fromDate;
    private LocalDate toDate;
    private String status;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
