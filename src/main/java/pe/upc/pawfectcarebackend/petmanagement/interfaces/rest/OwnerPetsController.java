package pe.upc.pawfectcarebackend.petmanagement.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetAllPetsByOwnerIdQuery;
import pe.upc.pawfectcarebackend.petmanagement.domain.services.PetQueryService;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources.PetResource;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.transform.PetResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/owners/{ownerId}/pets", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Owners")
@CrossOrigin(origins = {"https://pawfect-care-app-web.web.app","http://localhost:4200"})
public class OwnerPetsController {
    private final PetQueryService petQueryService;

    public OwnerPetsController(PetQueryService petQueryService) {
        this.petQueryService = petQueryService;
    }

    @GetMapping
    public ResponseEntity<List<PetResource>> getAllPetsByOwnerId(@PathVariable Long ownerId) {
        var getAllPetsByOwnerIdQuery = new GetAllPetsByOwnerIdQuery(ownerId);
        var pets = petQueryService.handle(getAllPetsByOwnerIdQuery);
        var petResources = pets.stream().map(PetResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResources);

    }

}
