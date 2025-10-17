package com.tekprymid.repository;

import com.tekprymid.entity.Appointment;
import com.tekprymid.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    // Find doctor by email for login
    Optional<Doctor> findByEmail(String email);

    // Check if email exists (for signup)
    boolean existsByEmail(String email);

    // Find doctor by name (optional search)
    Optional<Doctor> findByName(String name);

//    Optional<Appointment> findByDoctor_DoctorIdAndAppDateAndAppTime(int doctorId, LocalDate appDate, LocalTime appTime);
}
