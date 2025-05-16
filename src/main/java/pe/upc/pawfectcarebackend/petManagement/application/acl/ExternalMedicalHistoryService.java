package pe.upc.pawfectcarebackend.petmanagement.application.acl;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands.AddMedicalAppointmentToMedicalHistoryCommand;
import pe.upc.pawfectcarebackend.medicalrecords.interfaces.acl.MedicalHistoryContextFacade;

import java.util.Optional;

@Service
public class ExternalMedicalHistoryService {
    private final MedicalHistoryContextFacade medicalHistoryContextFacade;

    public ExternalMedicalHistoryService(MedicalHistoryContextFacade medicalHistoryContextFacade) {
        this.medicalHistoryContextFacade = medicalHistoryContextFacade;
    }
    public Optional<MedicalHistory> createMedicalHistory(
            String notes) {
        var medicalHistory = medicalHistoryContextFacade.createMedicalHistory(
                notes);
        return medicalHistory;
    }

    public Optional<MedicalHistory> fetchMedicalHistoryByPetId(Long medicalHistoryId) {
        var medicalHistory = medicalHistoryContextFacade.fetchMedicalHistoryById(medicalHistoryId);
        return medicalHistory;
    }

    public void AddMedicalAppointmentToMedicalHistory(Long medicalHistoryId, Long medicalAppointmentId) {
        medicalHistoryContextFacade.AddMedicalAppointmentToMedical(medicalHistoryId,medicalAppointmentId);
    }

}
