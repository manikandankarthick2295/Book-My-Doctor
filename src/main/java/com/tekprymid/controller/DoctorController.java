package com.tekprymid.controller;

import com.tekprymid.dto.LoginRequest;
import com.tekprymid.dto.SignupRequest;
import com.tekprymid.entity.Doctor;
import com.tekprymid.response.ResponseDet;
import com.tekprymid.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/signup")
    public ResponseDet signup(@RequestBody SignupRequest request) {
        return doctorService.signupDoctor(request);
    }

    @GetMapping("/login")
    public ResponseDet login(@RequestBody LoginRequest login) {
        return doctorService.loginDoctor(login);
    }
}
