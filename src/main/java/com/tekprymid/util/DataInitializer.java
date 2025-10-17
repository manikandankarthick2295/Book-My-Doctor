package com.tekprymid.util;


import com.tekprymid.constant.GeneralConstant;
import com.tekprymid.entity.Admin;
import com.tekprymid.entity.Role;
import com.tekprymid.repository.AdminRepository;
import com.tekprymid.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

//    private final UserRepository userRepository;
//    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create roles if not exists
        if (roleRepository.count() == 0) {

            Role adminRole = Role.builder().roleName(GeneralConstant.ADMIN).build();
            Role doctorRole = Role.builder().roleName(GeneralConstant.DOCTOR).build();
            Role userRole = Role.builder().roleName(GeneralConstant.USER).build();
            roleRepository.saveAll(List.of(adminRole, doctorRole, userRole));
        }

        // Create single admin
        if (adminRepository.count() == 0) {
            Role adminRole = roleRepository.findByRoleName(GeneralConstant.ADMIN).orElseThrow(()-> new RuntimeException(GeneralConstant.ROLE_NOT_FOUND));
            Admin admin = Admin.builder()
                    .name("Super Admin")
                    .email("admin@tekprymid.com")
                    .password("Admin@123") // In real-world, use encrypted password (BCrypt)
                    .role(adminRole)
                    .build();

            adminRepository.save(admin);
            System.out.println("✅ Admin user created successfully!");
        }
    }
}
