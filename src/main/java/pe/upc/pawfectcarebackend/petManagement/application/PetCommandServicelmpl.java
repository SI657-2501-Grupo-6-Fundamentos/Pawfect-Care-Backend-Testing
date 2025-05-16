package pe.upc.pawfectcarebackend.petmanagement.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.petmanagement.application.acl.ExternalMedicalHistoryService;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.CreatePetCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.DeletePetCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.UpdatePetCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.services.PetCommandService;
import pe.upc.pawfectcarebackend.petmanagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import pe.upc.pawfectcarebackend.petmanagement.infrastructure.persistence.jpa.repositories.PetRepository;

import java.util.Optional;

@Service
public class PetCommandServicelmpl implements PetCommandService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final ExternalMedicalHistoryService externalMedicalHistoryService;

    public PetCommandServicelmpl(PetRepository petRepository, OwnerRepository ownerRepository, ExternalMedicalHistoryService externalMedicalHistoryService) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.externalMedicalHistoryService = externalMedicalHistoryService;
    }


    @Override
    public Long handle(CreatePetCommand command) {
        var pet = new Pet(command);
        try {

            var owner = ownerRepository.findById(command.ownerId());
            if (owner.isEmpty()) throw new IllegalArgumentException("ownerId does not exist");
            pet.setOwner(owner.get());

            var newMedicalHistory = externalMedicalHistoryService.createMedicalHistory(
                    String.format("General notes for medical history %s ", pet.getPetName())
            );
            if (newMedicalHistory.isEmpty()) throw new IllegalArgumentException("Unable to create MedicalHistoryService");
            pet.setMedicalHistory(newMedicalHistory.get());

            petRepository.save(pet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving pet: " + e.getMessage());
        }
        return pet.getId();
    }


    @Override
    public Optional<Pet> handle(UpdatePetCommand command) {
        if (!petRepository.existsById(command.id())) throw new IllegalArgumentException("petId does not exist");
        var result = petRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Pet does not exist");
        var petToUpdate = result.get();
        try {
            var updatedPet = petRepository.save(petToUpdate.updateInformation(command.petName(), command.birthDate(), command.registrationDate(), command.animalBreed(), command.petGender()));
            return Optional.of(updatedPet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating pet: " + e.getMessage());
        }
    }


    @Override
    public void handle(DeletePetCommand command) {
        if (!petRepository.existsById(command.petId())) {
            throw new IllegalArgumentException("Pe does not exist");
        }
        try {
            petRepository.deleteById(command.petId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting pet: " + e.getMessage());
        }

    }
}
