package com.tekprymid.controller;

import com.tekprymid.dto.LoginRequest;
import com.tekprymid.dto.SignupRequest;
import com.tekprymid.response.ResponseDet;
import com.tekprymid.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDet signup(@RequestBody SignupRequest request) {
        return userService.signupUser(request);
    }

    @GetMapping("/login")
    public ResponseDet login(@RequestBody LoginRequest login) {
        return userService.loginUser(login);
    }
}
