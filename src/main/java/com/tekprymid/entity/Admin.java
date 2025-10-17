package com.tekprymid.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.print.Doc;
import java.util.List;

@Entity
@Table(name = "Admin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column(name = "Admin_name")
    private String name;

    private String password;

    private String email;

//    private List<LeaveRequest> leaveRqt;

//    private List<Doctor> doctor;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
