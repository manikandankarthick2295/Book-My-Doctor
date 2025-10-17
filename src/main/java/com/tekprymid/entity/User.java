package com.tekprymid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_name",nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(name = "phone_no",nullable = false)
    private long phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
