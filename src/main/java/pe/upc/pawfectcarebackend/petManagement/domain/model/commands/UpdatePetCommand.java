package pe.upc.pawfectcarebackend.petmanagement.domain.model.commands;


import pe.upc.pawfectcarebackend.petmanagement.domain.model.valueobjects.PetGender;

import java.time.LocalDate;

public record UpdatePetCommand(Long id, String petName, LocalDate birthDate,LocalDate registrationDate,
                               String animalBreed, PetGender petGender) {
}