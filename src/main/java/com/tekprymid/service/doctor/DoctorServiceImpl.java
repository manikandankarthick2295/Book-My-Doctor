package com.tekprymid.service.doctor;

import com.tekprymid.constant.GeneralConstant;
import com.tekprymid.dto.LoginRequest;
import com.tekprymid.dto.SignupRequest;
import com.tekprymid.entity.Doctor;
import com.tekprymid.entity.Role;
import com.tekprymid.repository.AdminRepository;
import com.tekprymid.repository.DoctorRepository;
import com.tekprymid.repository.RoleRepository;
import com.tekprymid.response.ResponseDet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;
    private final ResponseDet resDet = new ResponseDet();


@Override
    public ResponseDet signupDoctor(SignupRequest request) {
        Role doctorRole = roleRepository.findByRoleName(GeneralConstant.DOCTOR)
                .orElseThrow(() -> new RuntimeException(GeneralConstant.ROLE_NOT_FOUND));

        Doctor doctor = Doctor.builder()
                .name(request.getName())
                .email(request.getEmail())
                .pwd(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .spec(request.getSpecialist())
                .DOB(request.getDate())
                .rating(request.getRating())
                .role(doctorRole)
                .build();

        Doctor savedDoctor = doctorRepository.save(doctor);

        resDet.setError(false);
        resDet.setMessage(GeneralConstant.SIGNUP_SUCCESSFUL);
        resDet.setHttpStatus(HttpStatus.CREATED);
        resDet.setData(savedDoctor);
        return resDet;
    }

    public ResponseDet loginDoctor (LoginRequest login){
        if(login != null && login.getEmail() != null && login.getPassword() != null){
            Optional<Doctor> optDoctor = doctorRepository.findByEmail(login.getEmail());
            if(optDoctor.isEmpty()){
                resDet.setError(true);
                resDet.setMessage(GeneralConstant.DOCTOR_NOT_FOUND);
                resDet.setHttpStatus(HttpStatus.NOT_FOUND);
                resDet.setData(null);
            } else {
                Doctor doctor = optDoctor.get();
                if (!passwordEncoder.matches(login.getPassword(), doctor.getPwd())) {
                    resDet.setError(true);
                    resDet.setMessage(GeneralConstant.INVALID_PASSWORD);
                    resDet.setHttpStatus(HttpStatus.BAD_REQUEST);
                    resDet.setData(null);
                } else {
                    resDet.setError(false);
                    resDet.setMessage(GeneralConstant.LOGIN_SUCCESSFUL);
                    resDet.setHttpStatus(HttpStatus.FOUND);
                    resDet.setData(doctor);
                }

            }

        }else {
            resDet.setError(true);
            resDet.setMessage(GeneralConstant.INVALID_REQUEST);
            resDet.setHttpStatus(HttpStatus.BAD_REQUEST);
            resDet.setData(null);
        }
        return resDet;
    }
}
