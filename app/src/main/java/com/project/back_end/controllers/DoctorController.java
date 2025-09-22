package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {

    private List<Doctor> doctors;

    public DoctorController() {
        this.doctors = new ArrayList<>();
    }

    // Create doctor
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added: " + doctor.getName());
    }

    // Read all doctors
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    // Find doctor by ID
    public Doctor getDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId() == id) {
                return doctor;
            }
        }
        return null;
    }

    // Update doctor info
    public boolean updateDoctor(int id, String newName, String newSpecialty) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null) {
            doctor.setName(newName);
            doctor.setSpecialty(newSpecialty);
            System.out.println("Doctor updated: " + doctor.getName());
            return true;
        }
        return false;
    }

    // Delete doctor
    public boolean deleteDoctor(int id) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null) {
            doctors.remove(doctor);
            System.out.println("Doctor deleted: " + doctor.getName());
            return true;
        }
        return false;
    }
}
