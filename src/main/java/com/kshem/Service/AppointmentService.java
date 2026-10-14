package com.kshem.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kshem.Exception.AppointmentNotFoundException;
import com.kshem.Model.Appointment;

@Service
public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>();

    
    //Book Appointment
    public String bookAppointment(Appointment appointment) {

        for (Appointment a : appointments) {
            if (a.getAppointmentId() == appointment.getAppointmentId()) {
                return "Appointment ID already exists";
            }
        }
        appointments.add(appointment);
        return "Appointment booked successfully";
    }

    
    
    
    //Fetch all appointments
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    
    
    //Fetch appointment by ID
    public Appointment getAppointmentById(int id) {

        for (Appointment a : appointments) {
            if (a.getAppointmentId() == id) {
                return a;
            }
        }
        throw new AppointmentNotFoundException("Appointment not found with ID: " + id);
    }

    
    
    
    //Search appointments by partial doctor name
    public List<Appointment> getAppointmentsByDoctor(String doctorName) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment a : appointments) {
            if (a.getDoctorName() != null
                    && a.getDoctorName().toLowerCase()
                    .contains(doctorName.toLowerCase())) {
                result.add(a);
            }
        }

        return result;
    }

    
    
    
    //Search appointments by partial patient name
    public List<Appointment> getAppointmentsByPatient(String patientName) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment a : appointments) {
            if (a.getPatientName() != null
                    && a.getPatientName().toLowerCase()
                    .contains(patientName.toLowerCase())) {
                result.add(a);
            }
        }

        return result;
    }

    
    
    
    
    //Cancel appointment by ID
    public String cancelAppointment(int id) {

        boolean removed = appointments.removeIf(a -> a.getAppointmentId() == id);

        if (removed) {
            return "Appointment cancelled successfully";
        }

        throw new AppointmentNotFoundException("Appointment not found with ID: " + id);
    }

    
    
    //Update an existing appointment
    public String updateAppointment(int id, Appointment updated) {

        for (Appointment a : appointments) {
            if (a.getAppointmentId() == id) {

                a.setPatientName(updated.getPatientName());
                a.setDoctorName(updated.getDoctorName());
                a.setDisease(updated.getDisease());
                a.setAppointmentDate(updated.getAppointmentDate());

                return "Appointment updated successfully";
            }
        }

        throw new AppointmentNotFoundException(
                "Appointment not found with ID: " + id);
    }

    
    
    
    //Fetch appointments by date
    public List<Appointment> getAppointmentsByDate(LocalDate date) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment a : appointments) {
            if (a.getAppointmentDate() != null
                    && a.getAppointmentDate().equals(date)) {
                result.add(a);
            }
        }

        return result;
    }

    //Get total appointment count
    public int getAppointmentCount() {
        return appointments.size();
    }
}
