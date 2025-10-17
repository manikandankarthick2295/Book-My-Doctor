package com.tekprymid.service.appointment;

import com.tekprymid.dto.AppointmentRequest;
import com.tekprymid.entity.Appointment;
import com.tekprymid.entity.Doctor;
import com.tekprymid.response.ResponseDet;

import java.util.List;

public interface AppointmentService {
    ResponseDet bookAppointment(AppointmentRequest request);

}
