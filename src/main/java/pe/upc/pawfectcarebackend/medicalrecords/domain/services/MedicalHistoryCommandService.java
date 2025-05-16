package pe.upc.pawfectcarebackend.medicalrecords.domain.services;

import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands.AddMedicalAppointmentToMedicalHistoryCommand;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands.CreateMedicalHistoryCommand;

import java.util.Optional;

public interface MedicalHistoryCommandService {
    Optional<MedicalHistory> handle(CreateMedicalHistoryCommand command);
    void handle(AddMedicalAppointmentToMedicalHistoryCommand command);
}
