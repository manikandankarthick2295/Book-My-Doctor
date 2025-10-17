package com.tekprymid.util;


import com.tekprymid.dto.DoctorDto;
import com.tekprymid.dto.UserDto;
import com.tekprymid.dto.LeaveRequestDto;
import com.tekprymid.entity.Doctor;
import com.tekprymid.entity.User;
import com.tekprymid.entity.LeaveRequest;

public class EntityDtoConverter {

    // Doctor entity → DTO
    public static DoctorDto convertToDoctorDto(Doctor doctor) {
        DoctorDto dto = new DoctorDto();
        dto.setDoctorId(doctor.getDoctorId());
        dto.setName(doctor.getName());
        dto.setSpec(doctor.getSpec());
        dto.setPhone(doctor.getPhone());
        dto.setRating(doctor.getRating());
        dto.setDOB(doctor.getDOB());
        dto.setEmail(doctor.getEmail());
        return dto;
    }

    // DTO → Doctor entity
    public static Doctor convertToDoctorEntity(DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(dto.getDoctorId());
        doctor.setName(dto.getName());
        doctor.setSpec(dto.getSpec());
        doctor.setPhone(dto.getPhone());
        doctor.setRating(dto.getRating());
        doctor.setDOB(dto.getDOB());
        doctor.setEmail(dto.getEmail());
        return doctor;
    }

    // User entity → DTO
    public static UserDto convertToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setGender(user.getGender());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        return dto;
    }

    // DTO → User entity
    public static User convertToUserEntity(UserDto dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setGender(dto.getGender());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        return user;
    }

    // LeaveRequest entity → DTO
    public static LeaveRequestDto convertToLeaveRequestDto(LeaveRequest leaveRequest) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setLeaveRqtId(leaveRequest.getLeaveRqtId());
        dto.setFromDate(leaveRequest.getFromDate());
        dto.setToDate(leaveRequest.getToDate());
        dto.setStatus(leaveRequest.getStatus());
        dto.setReason(leaveRequest.getReason());
        dto.setDoctorId(leaveRequest.getDoctor().getDoctorId());
        return dto;
    }

    // DTO → LeaveRequest entity
    public static LeaveRequest convertToLeaveRequestEntity(LeaveRequestDto dto) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setLeaveRqtId(dto.getLeaveRqtId());
        leaveRequest.setFromDate(dto.getFromDate());
        leaveRequest.setToDate(dto.getToDate());
        leaveRequest.setStatus(dto.getStatus());
        leaveRequest.setReason(dto.getReason());
        return leaveRequest;
    }
}

