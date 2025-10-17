package com.tekprymid.service.admin;

import com.tekprymid.dto.LoginRequest;
import com.tekprymid.response.ResponseDet;

public interface AdminService {
    ResponseDet loginAdmin(LoginRequest login);
}
