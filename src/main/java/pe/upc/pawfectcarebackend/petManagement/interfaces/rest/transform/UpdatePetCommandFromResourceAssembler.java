package pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.transform;

import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.UpdatePetCommand;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources.UpdatePetResource;

public class UpdatePetCommandFromResourceAssembler {
    public static UpdatePetCommand toCommandFromResource(Long petId, UpdatePetResource resource) {
        return new UpdatePetCommand(
                petId,
                resource.petName(),
                resource.birthDate(),
                resource.registrationDate(),
                resource.animalBreed(),
                resource.petGender());
    }
}
