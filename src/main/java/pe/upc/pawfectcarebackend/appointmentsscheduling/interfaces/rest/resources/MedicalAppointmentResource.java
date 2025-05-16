package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources;

import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.valueobjects.AppointmentStatus;

import java.time.LocalDateTime;

public record MedicalAppointmentResource (
        Long id,
        String diagnosis,
        String notes,
        String treatment,
        Long appointmentId) {
}
