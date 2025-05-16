package pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands;

public record AddMedicalAppointmentToMedicalHistoryCommand(Long medicalHistoryId, Long medicalAppointmentId) {
}
