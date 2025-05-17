package pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.transform;

import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.CreateOwnerCommand;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources.CreateOwnerResource;

public class CreateOwnerCommandFromResourceAssembler {
    public static CreateOwnerCommand toCommandFromResource(CreateOwnerResource resource) {
        return new CreateOwnerCommand(
                resource.fullName(),
                resource.phoneNumber(),
                resource.email(),
                resource.address());
    }
}

