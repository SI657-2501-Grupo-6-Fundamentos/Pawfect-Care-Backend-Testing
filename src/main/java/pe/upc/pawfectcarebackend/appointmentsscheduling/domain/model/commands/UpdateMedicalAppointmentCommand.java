package pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands;

public record UpdateMedicalAppointmentCommand(
        Long medicalAppointmentId,
        String diagnosis,
        String notes,
        String treatment
) {
}
