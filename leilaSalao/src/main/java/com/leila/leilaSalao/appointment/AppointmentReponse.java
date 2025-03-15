package com.leila.leilaSalao.appointment;

import java.time.LocalDateTime;

import com.leila.leilaSalao.model.UserResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentReponse{

    private Long id;
    private UserResponse user;
    private String service;
    private LocalDateTime date;
    private String status;

    public AppointmentReponse(Appointment appointment) {
        this.id = appointment.getId();
        this.user = new UserResponse();
        this.service = appointment.getService();
        this.date = appointment.getDate();
        this.status = appointment.getStatus();
    }
}