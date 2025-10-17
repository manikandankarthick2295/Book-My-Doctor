package com.tekprymid.service.user;

import com.tekprymid.dto.LoginRequest;
import com.tekprymid.dto.SignupRequest;
import com.tekprymid.response.ResponseDet;

public interface UserService {
    ResponseDet signupUser(SignupRequest request);
    ResponseDet loginUser(LoginRequest login);
}
