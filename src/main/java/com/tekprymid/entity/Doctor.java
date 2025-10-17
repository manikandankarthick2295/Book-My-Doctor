package com.tekprymid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Doctor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    @Column(name = "doctor_name",nullable = false)
    private String name;

    @Column(name = "specialist",nullable = false)
    private String spec;

    @Column(name = "phone_no",nullable = false)
    private long phone;

    @Column(name = "rating",nullable = false)
    private double rating;

    @Column(name = "date_of_birth",nullable = false)
    private LocalDate DOB;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String pwd;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointment;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<LeaveRequest> leaveRqt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



}
