package com.tekprymid.service.appointment;

import com.tekprymid.constant.GeneralConstant;
import com.tekprymid.dto.AppointmentRequest;
import com.tekprymid.entity.Appointment;
import com.tekprymid.entity.Doctor;
import com.tekprymid.entity.User;
import com.tekprymid.repository.AppointmentRepository;
import com.tekprymid.repository.DoctorRepository;
import com.tekprymid.repository.UserRepository;
import com.tekprymid.response.ResponseDet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDet bookAppointment(AppointmentRequest request) {
        ResponseDet resDet = new ResponseDet();
        System.out.println("hi 1");

//        try {
            if (request == null || request.getDoctorId() == 0 || request.getUserId() == 0
                    || request.getAppDate() == null || request.getAppTime() == null) {
                resDet.setError(true);
                resDet.setMessage(GeneralConstant.INVALID_REQUEST);
                resDet.setHttpStatus(HttpStatus.BAD_REQUEST);
                resDet.setData(null);
                return resDet;
            }
            System.out.println("hi 2");

            Optional<Doctor> doctorOpt = doctorRepository.findById(request.getDoctorId());
            Optional<User> userOpt = userRepository.findById(request.getUserId());

            if (doctorOpt.isEmpty() || userOpt.isEmpty()) {
                resDet.setError(true);
                resDet.setMessage(GeneralConstant.DOCTOR_OR_USER_NOT_FOUND);
                resDet.setHttpStatus(HttpStatus.NOT_FOUND);
                resDet.setData(null);
                return resDet;
            }
            System.out.println("hi 3");

            Doctor doctor = doctorOpt.get();
            List<Appointment> doctorAppointments = appointmentRepository.findByDoctor(doctor);

// Initialize a flag to check if the slot is already booked
            boolean isBooked = false;

// Loop through each appointment
            for (Appointment appointment : doctorAppointments) {
                // Check if the date and time match the requested appointment
                if (appointment.getAppDate().equals(request.getAppDate()) &&
                        appointment.getAppTime().equals(request.getAppTime())) {
                    isBooked = true;
                    break; // no need to check further once we found a conflict
                }
            }

// Now you can use the isBooked flag
            if (isBooked) {
                resDet.setError(true);
                resDet.setMessage("No appointment will be scheduled at that time or date. Please choose another slot.");
                resDet.setHttpStatus(HttpStatus.CONFLICT);
                resDet.setData(null);
                return resDet;
            }

            System.out.println("hi 4");

            Appointment appointment = Appointment.builder()
                    .doctor(doctorOpt.get())
                    .user(userOpt.get())
                    .appDate(request.getAppDate())
                    .appTime(request.getAppTime())
                    .createdDate(LocalDate.now())
                    .build();
            System.out.println("hi 5");

            appointmentRepository.save(appointment);

            resDet.setError(false);
            resDet.setMessage(GeneralConstant.APPOINTMENT_BOOKED_SUCCESS);
            resDet.setHttpStatus(HttpStatus.CREATED);
            resDet.setData(appointment);
            System.out.println("hi 6");
//        } catch (Exception e) {
//            resDet.setError(true);
//            resDet.setMessage("Something went wrong while booking appointment.");
//            resDet.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//            resDet.setData(null);
//        }

        return resDet;
    }
}
