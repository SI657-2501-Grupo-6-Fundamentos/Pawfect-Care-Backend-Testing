package pe.upc.pawfectcarebackend.petmanagement.domain.services;

import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Pet;

import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.CreatePetCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.DeletePetCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.UpdatePetCommand;

import java.util.Optional;

public interface PetCommandService {
    Optional<Pet> handle(UpdatePetCommand command);
    Long handle(CreatePetCommand command);
    void handle(DeletePetCommand command);
}
