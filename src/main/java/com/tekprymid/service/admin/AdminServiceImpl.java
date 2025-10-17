package com.tekprymid.service.admin;

import com.tekprymid.constant.GeneralConstant;
import com.tekprymid.dto.LoginRequest;
import com.tekprymid.entity.Admin;
import com.tekprymid.entity.Doctor;
import com.tekprymid.repository.AdminRepository;
import com.tekprymid.repository.DoctorRepository;
import com.tekprymid.response.ResponseDet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ResponseDet resDet = new ResponseDet();

    @Override
    public ResponseDet loginAdmin(LoginRequest login) {
        if (login == null || login.getEmail() == null || login.getPassword() == null) {
            resDet.setError(true);
            resDet.setMessage(GeneralConstant.INVALID_REQUEST);
            resDet.setHttpStatus(HttpStatus.BAD_REQUEST);
            return resDet;
        }

        Optional<Admin> optAdmin = adminRepository.findByEmail(login.getEmail());
        if (optAdmin.isEmpty()) {
            resDet.setError(true);
            resDet.setMessage(GeneralConstant.ADMIN_NOT_FOUND);
            resDet.setHttpStatus(HttpStatus.NOT_FOUND);
            return resDet;
        }

        Admin admin = optAdmin.get();
        if (!passwordEncoder.matches(login.getPassword(), admin.getPassword())) {
            resDet.setError(true);
            resDet.setMessage(GeneralConstant.INVALID_PASSWORD);
            resDet.setHttpStatus(HttpStatus.BAD_REQUEST);
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
