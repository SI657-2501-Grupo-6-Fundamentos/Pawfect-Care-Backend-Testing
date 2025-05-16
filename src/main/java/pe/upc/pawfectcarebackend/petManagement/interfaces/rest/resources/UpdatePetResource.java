package pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources;

import pe.upc.pawfectcarebackend.petmanagement.domain.model.valueobjects.PetGender;

import java.time.LocalDate;

public record UpdatePetResource(
        String petName,
        LocalDate birthDate,
        LocalDate registrationDate,
        String animalBreed,
        PetGender petGender) {

}
