package pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands;

public record CreateMedicalAppointmentCommand(
         String diagnosis,
         String treatment,
         String notes,
         Long appointmentId,
         Long medicalHistoryId
) {
}
