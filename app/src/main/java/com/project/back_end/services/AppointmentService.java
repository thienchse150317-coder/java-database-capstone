package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Lấy tất cả lịch hẹn
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Lấy lịch hẹn theo id
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // ✅ Đặt lịch hẹn mới (book appointment) dùng repository
    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Cập nhật lịch hẹn
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setDoctor(appointmentDetails.getDoctor());
            appointment.setPatient(appointmentDetails.getPatient());
            appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
            return appointmentRepository.save(appointment);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    // Xóa lịch hẹn
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // ✅ Lấy lịch hẹn theo doctorId và ngày
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                doctorId, startOfDay, endOfDay
        );
    }
}
