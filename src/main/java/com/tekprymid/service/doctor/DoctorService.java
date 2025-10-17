package com.tekprymid.service.doctor;

import com.tekprymid.dto.LoginRequest;
import com.tekprymid.dto.SignupRequest;
import com.tekprymid.entity.Doctor;
import com.tekprymid.response.ResponseDet;

public interface DoctorService {

    ResponseDet signupDoctor(SignupRequest request);
    ResponseDet loginDoctor (LoginRequest login);


}
