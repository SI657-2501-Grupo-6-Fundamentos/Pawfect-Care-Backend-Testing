/**
 * Pet aggregate
 * @Summary
 * The petmanagement  class is an aggregate that represents a  petmanagement .
 */
package pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.CreatePetCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.valueobjects.PetGender;
import pe.upc.pawfectcarebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Getter
@Entity
public class Pet extends AuditableAbstractAggregateRoot<Pet> {

    private String petName;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private String animalBreed;
    @Enumerated(EnumType.STRING)
    private PetGender petGender;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NotNull
    @JsonIgnore
    private Owner owner;

    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_history_id")
    private MedicalHistory medicalHistory;



    public Pet() {
        this.petName = Strings.EMPTY;
        this.birthDate = LocalDate.now();
        this.registrationDate = LocalDate.now();
        this.animalBreed = Strings.EMPTY;
        this.petGender = PetGender.FEMALE;
    }
    public Pet updateInformation(String petName, LocalDate birthDate,LocalDate registrationDate, String animalBreed, PetGender petGender) {
        this.petName = petName;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.animalBreed = animalBreed;
        this.petGender = petGender;
        return this;
    }

    public Pet(CreatePetCommand command) {
        this.petName = command.petName();
        this.birthDate = command.birthDate();
        this.registrationDate = command.registrationDate();
        this.animalBreed = command.animalBreed();
        this.petGender = command.petGender();
    }


}