package pe.upc.pawfectcarebackend.medicalrecords.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.queries.GetMedicalHistoryByPetIdQuery;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.queries.GetMedicalHistoryByIdQuery;
import pe.upc.pawfectcarebackend.medicalrecords.domain.services.MedicalHistoryQueryService;
import pe.upc.pawfectcarebackend.medicalrecords.infrastructure.persistence.jpa.repositories.MedicalHistoryRepository;


import java.util.Optional;

@Service
public class MedicalHistoryQueryServicelmpl  implements MedicalHistoryQueryService {
    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryQueryServicelmpl(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @Override
    public Optional<MedicalHistory> handle(GetMedicalHistoryByIdQuery query) {
        if (!medicalHistoryRepository.existsById(query.medicalHistoryId()))  throw new IllegalArgumentException("medicalHistoryId not found");
        return medicalHistoryRepository.findById(query.medicalHistoryId());
    }

    @Override
    public Optional<MedicalHistory> handle(GetMedicalHistoryByPetIdQuery query) {
        if (!medicalHistoryRepository.existsByPetId(query.petId())) throw new IllegalArgumentException("medicalHistoryId not found with pet");
        return medicalHistoryRepository.findByPetId(query.petId());
    }
}
