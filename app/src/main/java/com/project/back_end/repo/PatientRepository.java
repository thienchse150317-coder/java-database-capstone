package com.project.back_end.repo;

import com.project.back_end.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    // Find patient by email
    Optional<Patient> findByEmail(String email);

    // Find patient by email or phone number
    Optional<Patient> findByEmailOrPhone(String email, String phone);
}
