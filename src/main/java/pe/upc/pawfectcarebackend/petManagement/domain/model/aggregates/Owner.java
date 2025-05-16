package pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates;

import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.CreateOwnerCommand;
import pe.upc.pawfectcarebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Owner extends AuditableAbstractAggregateRoot<Owner> {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
//    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Pet> pets;

    public Owner() {
        this.fullName = Strings.EMPTY;
        this.phoneNumber = Strings.EMPTY;
        this.email = Strings.EMPTY;
        this.address = Strings.EMPTY;
//        this.pets = new ArrayList<>();
    }
    public Owner(CreateOwnerCommand command) {
        this();
        this.fullName = command.fullName();
        this.phoneNumber = command.phoneNumber();
        this.email = command.email();
        this.address = command.address();
    }
    public Owner updateInformation(String fullName, String phoneNumber, String email, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        return this;
    }
//    public void addPetToOwner(Pet pet) {
//        pets.add(pet);
//    }

}
