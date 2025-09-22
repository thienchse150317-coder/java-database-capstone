package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private List<Appointment> appointments;

    public AppointmentService() {
        this.appointments = new ArrayList<>();
    }

    // Create appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment created with ID: " + appointment.getAppointmentId());
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    // Find appointment by ID
    public Appointment getAppointmentById(int id) {
        for (Appointment appt : appointments) {
            if (appt.getAppointmentId() == id) {
                return appt;
            }
        }
        return null;
    }

    // Update appointment details
    public boolean updateAppointment(int id, String newDate, String newTime) {
        Appointment appt = getAppointmentById(id);
        if (appt != null) {
            appt.setDate(newDate);
            appt.setTime(newTime);
            System.out.println("Appointment updated with ID: " + appt.getAppointmentId());
            return true;
        }
        return false;
    }

    // Delete appointment
    public boolean deleteAppointment(int id) {
        Appointment appt = getAppointmentById(id);
        if (appt != null) {
            appointments.remove(appt);
            System.out.println("Appointment deleted with ID: " + appt.getAppointmentId());
            return true;
        }
        return false;
    }
}
