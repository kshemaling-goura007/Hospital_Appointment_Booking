package com.kshem.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.kshem.Model.Appointment;
import com.kshem.Service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    //Book a new appointment
    @PostMapping("/appoint")
    public String bookAppointment(@Valid @RequestBody Appointment appointment) {
        return service.bookAppointment(appointment);
    }

    //Fetch all appointments
    @GetMapping("/allAppoint")
    public List<Appointment> getAllAppointments() {
        return service.getAllAppointments();
    }

    //Fetch appointment by ID
    @GetMapping("/app/{id}")
    public Appointment getAppointmentById(@PathVariable int id) {
        return service.getAppointmentById(id);
    }

    //Search by partial doctor name
    @GetMapping("/doctor")
    public List<Appointment> getAppointmentsByDoctor(@RequestParam String name) {
        return service.getAppointmentsByDoctor(name);
    }

    //Search by partial patient name
    @GetMapping("/patient")
    public List<Appointment> getAppointmentsByPatient(@RequestParam String name) {
        return service.getAppointmentsByPatient(name);
    }

    //Cancel appointment by ID
    @DeleteMapping("/delete/{id}")
    public String cancelAppointment(@PathVariable int id) {
        return service.cancelAppointment(id);
    }

    //Update appointment by ID
    @PutMapping("/update/{id}")
    public String updateAppointment(@PathVariable int id, @Valid @RequestBody Appointment appointment) {
        return service.updateAppointment(id, appointment);
    }

    //Fetch appointments by date
    @GetMapping("/date/{date}")
    public List<Appointment> getAppointmentsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return service.getAppointmentsByDate(date);
    }

    //Get total appointment count
    @GetMapping("/count")
    public int getAppointmentCount() {
        return service.getAppointmentCount();
    }
}
