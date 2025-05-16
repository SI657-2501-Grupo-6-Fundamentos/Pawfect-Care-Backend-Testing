package pe.upc.pawfectcarebackend.medicalrecords.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands.AddMedicalAppointmentToMedicalHistoryCommand;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands.CreateMedicalHistoryCommand;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.queries.GetMedicalHistoryByIdQuery;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.queries.GetMedicalHistoryByPetIdQuery;
import pe.upc.pawfectcarebackend.medicalrecords.domain.services.MedicalHistoryCommandService;
import pe.upc.pawfectcarebackend.medicalrecords.domain.services.MedicalHistoryQueryService;


import java.util.Optional;

@Service
public class MedicalHistoryContextFacade {
    private final MedicalHistoryQueryService medicalHistoryQueryService;
    private final MedicalHistoryCommandService medicalHistoryCommandService;

    public MedicalHistoryContextFacade(
            MedicalHistoryQueryService medicalHistoryQueryService,
            MedicalHistoryCommandService medicalHistoryCommandService
    ) {
        this.medicalHistoryQueryService = medicalHistoryQueryService;
        this.medicalHistoryCommandService = medicalHistoryCommandService;
    }

    public Optional<MedicalHistory> fetchMedicalHistoryById(Long medicalHistoryId) {
        var getMedicalHistoryByIdQuery = new GetMedicalHistoryByIdQuery(medicalHistoryId);
        var medicalHistory = medicalHistoryQueryService.handle(getMedicalHistoryByIdQuery);
        return medicalHistory;
    }

    public Optional<MedicalHistory> fetchMedicalHistoryByPetId(Long petId) {
        var getMedicalHistoryByPetIdQuery = new GetMedicalHistoryByPetIdQuery(petId);
        var medicalHistory = medicalHistoryQueryService.handle(getMedicalHistoryByPetIdQuery);
        return medicalHistory;
    }


    public Optional<MedicalHistory> createMedicalHistory(
            String notes) {
        var createMedicalHistoryCommand = new CreateMedicalHistoryCommand(
                notes);
        var medicalHistory = medicalHistoryCommandService.handle(createMedicalHistoryCommand);
        return medicalHistory;
    }


    public void AddMedicalAppointmentToMedical(Long medicalHistoryId, Long medicalAppointmentId) {
        var addMedicalAppointmentToMedicalHistoryCommand = new AddMedicalAppointmentToMedicalHistoryCommand(
                medicalHistoryId,medicalAppointmentId);
        medicalHistoryCommandService.handle(addMedicalAppointmentToMedicalHistoryCommand);
    }

}
