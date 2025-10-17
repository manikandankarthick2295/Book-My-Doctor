package com.tekprymid.controller;

import com.tekprymid.dto.AppointmentRequest;
import com.tekprymid.response.ResponseDet;
import com.tekprymid.service.appointment.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<ResponseDet> bookAppointment(@RequestBody AppointmentRequest request) {
        ResponseDet response = appointmentService.bookAppointment(request);
        return ResponseEntity.ok(response);
    }
}
