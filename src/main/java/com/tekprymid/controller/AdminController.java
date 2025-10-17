package com.tekprymid.controller;

import com.tekprymid.dto.LoginRequest;
import com.tekprymid.response.ResponseDet;
import com.tekprymid.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/login")
    public ResponseDet login(@RequestBody LoginRequest login) {
        return adminService.loginAdmin(login);
    }
}
