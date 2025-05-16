package pe.upc.pawfectcarebackend.medicalrecords.application;


import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.medicalrecords.application.acl.ExternalMedicalAppointmentService;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands.AddMedicalAppointmentToMedicalHistoryCommand;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands.CreateMedicalHistoryCommand;
import pe.upc.pawfectcarebackend.medicalrecords.domain.services.MedicalHistoryCommandService;
import pe.upc.pawfectcarebackend.medicalrecords.infrastructure.persistence.jpa.repositories.MedicalHistoryRepository;

import java.util.Optional;

@Service
public class MedicalHistoryCommandServicelmpl implements MedicalHistoryCommandService {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final ExternalMedicalAppointmentService externalMedicalAppointmentService;

    public MedicalHistoryCommandServicelmpl(MedicalHistoryRepository medicalHistoryRepository,
                                            ExternalMedicalAppointmentService externalMedicalAppointmentService) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.externalMedicalAppointmentService = externalMedicalAppointmentService;
    }

    @Override
    public Optional<MedicalHistory> handle(CreateMedicalHistoryCommand command) {
     MedicalHistory medicalHistory = new MedicalHistory(command);
        try {
            medicalHistoryRepository.save(medicalHistory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving medicalHistory: " + e.getMessage());
        }

        return Optional.of(medicalHistory);
    }

    @Override
    public  void handle(AddMedicalAppointmentToMedicalHistoryCommand command) {
        if (!medicalHistoryRepository.existsById(command.medicalHistoryId()))  throw new IllegalArgumentException("medicalHistory  does not exist");
        var medicalAppointment = externalMedicalAppointmentService.fetchMedicalAppointmentById(command.medicalAppointmentId());
        if(medicalAppointment.isEmpty()) throw new IllegalArgumentException("medicalAppointment  does not exist");

        try {
                medicalHistoryRepository.findById(command.medicalHistoryId()).map(medicalHistory -> {
                medicalHistory.addMedicalAppointmentToMedicalHistory(medicalAppointment.get());
                medicalHistoryRepository.save(medicalHistory);
                System.out.println("Medical Appointment added to medicalHistory");
                return medicalAppointment.get().getId();
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while adding Appointment to medicalHistory : " + e.getMessage());
        }



    }

}
