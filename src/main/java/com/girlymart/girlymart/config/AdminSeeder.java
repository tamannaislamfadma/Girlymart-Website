package com.girlymart.girlymart.config;

import com.girlymart.girlymart.model.User;
import com.girlymart.girlymart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // The phone number you want to use for the admin
        String adminPhone = "01676281583";

        // Check if the admin already exists so we don't duplicate it
        if (!userRepository.existsByPhone(adminPhone)) {
            User admin = User.builder()
                    .phone(adminPhone)
                    .password(passwordEncoder.encode("administheboss")) // Your plain text password
                    .name("Super Admin")
                    .address("GirlyMart HQ, Dhaka")
                    .deliveryPhone("01700000000")
                    .role("ADMIN")
                    .build();

            userRepository.save(admin);
            System.out.println("✅ SUCCESS: Admin user was automatically created!");
        } else {
            System.out.println("✅ Admin user already exists in the database.");
        }
    }
}