package pe.upc.pawfectcarebackend.petmanagement.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetPetByIdQuery;
import pe.upc.pawfectcarebackend.petmanagement.domain.services.PetQueryService;

import java.util.Optional;

@Service
public class PetContextFacade {
    private final PetQueryService petQueryService;


    public PetContextFacade( PetQueryService petQueryService) {
        this.petQueryService = petQueryService;
    }

    public Optional<Pet> fetchPetById(Long petId) {
        var getPetByIdQuery = new GetPetByIdQuery(petId);
        var pet = petQueryService.handle(getPetByIdQuery);
        return pet;

    }

}
