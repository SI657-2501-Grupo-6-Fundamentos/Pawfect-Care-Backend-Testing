package pe.upc.pawfectcarebackend.petmanagement.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Owner;
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByEmail(String email);
    boolean existsById(Long ownerId);
}
