package com.leila.leilaSalao.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leila.leilaSalao.model.User;
import com.leila.leilaSalao.repository.UserRepository;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    public Appointment createAppointment(Appointment appointment, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        appointment.setUser(user);

        if (appointment.getService() == null || appointment.getDate() == null || appointment.getStatus() == null) {
            throw new IllegalArgumentException("Todos os campos do agendamento são obrigatórios.");
        }

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }
}