package com.tekprymid.service.user;

import com.tekprymid.constant.GeneralConstant;
import com.tekprymid.dto.LoginRequest;
import com.tekprymid.dto.SignupRequest;
import com.tekprymid.entity.Doctor;
import com.tekprymid.entity.Role;
import com.tekprymid.entity.User;
import com.tekprymid.repository.AdminRepository;
import com.tekprymid.repository.DoctorRepository;
import com.tekprymid.repository.RoleRepository;
import com.tekprymid.repository.UserRepository;
import com.tekprymid.response.ResponseDet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DoctorRepository doctorRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ResponseDet resDet = new ResponseDet();

    public ResponseDet signupUser(SignupRequest request) {
        Role userRole = roleRepository.findByRoleName(GeneralConstant.USER)
                .orElseThrow(() -> new RuntimeException(GeneralConstant.ROLE_NOT_FOUND));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .gender(request.getGender())
                .role(userRole)
                .build();

        userRepository.save(user);

        resDet.setError(false);
        resDet.setMessage(GeneralConstant.SIGNUP_SUCCESSFUL);
        resDet.setHttpStatus(HttpStatus.CREATED);
        resDet.setData(user);
        return resDet;
    }

    public ResponseDet loginUser(LoginRequest login) {
        if (login == null || login.getEmail() == null || login.getPassword() == null) {
            resDet.setError(true);
            resDet.setMessage(GeneralConstant.INVALID_REQUEST);
            resDet.setHttpStatus(HttpStatus.BAD_REQUEST);
            resDet.setData(null);
            return resDet;
        }

        Optional<User> optUser = userRepository.findByEmail(login.getEmail());
        if (optUser.isEmpty()) {
            resDet.setError(true);
            resDet.setMessage(GeneralConstant.USER_NOT_FOUND);
            resDet.setHttpStatus(HttpStatus.NOT_FOUND);
            resDet.setData(null);
            return resDet;
        }

        User user = optUser.get();
        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            resDet.setError(true);
            resDet.setMessage(GeneralConstant.INVALID_PASSWORD);
            resDet.setHttpStatus(HttpStatus.BAD_REQUEST);
            resDet.setData(null);
        } else {
            List<Doctor> doctorList = doctorRepository.findAll();
            resDet.setError(false);
            resDet.setMessage(GeneralConstant.LOGIN_SUCCESSFUL);
            resDet.setHttpStatus(HttpStatus.OK);
            resDet.setData(doctorList);
        }
        return resDet;
    }
}
