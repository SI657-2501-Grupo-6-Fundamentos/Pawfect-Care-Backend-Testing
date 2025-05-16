package pe.upc.pawfectcarebackend.medicalrecords.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;

import java.util.Optional;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

    Boolean existsByPetId(Long petId);
    Optional<MedicalHistory> findByPetId(Long petId);
}
