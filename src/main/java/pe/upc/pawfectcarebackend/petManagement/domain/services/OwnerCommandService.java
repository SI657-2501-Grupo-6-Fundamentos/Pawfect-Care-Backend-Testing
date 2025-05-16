package pe.upc.pawfectcarebackend.petmanagement.domain.services;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Owner;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.CreateOwnerCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.UpdateOwnerCommand;
import java.util.Optional;

public interface OwnerCommandService {
    Optional<Owner> handle(UpdateOwnerCommand command);
    Long handle(CreateOwnerCommand command);

}
