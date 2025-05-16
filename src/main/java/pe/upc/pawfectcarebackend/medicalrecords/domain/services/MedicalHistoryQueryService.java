package pe.upc.pawfectcarebackend.medicalrecords.domain.services;

import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.queries.GetMedicalHistoryByPetIdQuery;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.queries.GetMedicalHistoryByIdQuery;

import java.util.Optional;

public interface MedicalHistoryQueryService {
   Optional<MedicalHistory> handle(GetMedicalHistoryByIdQuery query);
   Optional<MedicalHistory> handle(GetMedicalHistoryByPetIdQuery query);

}
