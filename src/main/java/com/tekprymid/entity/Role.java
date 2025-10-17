package com.tekprymid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    private String roleName;
//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    private List<Doctor> doctor;
//
//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    private List<User> user;
//
//    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL)
//    private Admin admin;
}
