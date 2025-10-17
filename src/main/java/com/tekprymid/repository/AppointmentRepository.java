package com.tekprymid.repository;

import com.tekprymid.entity.Appointment;
import com.tekprymid.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

//    Optional<Appointment> findByDoctor_DoctorIdAndAppDateAndAppTime(int doctorId, LocalDate appDate, LocalTime appTime);
List<Appointment> findByDoctor(Doctor doctor);
}

