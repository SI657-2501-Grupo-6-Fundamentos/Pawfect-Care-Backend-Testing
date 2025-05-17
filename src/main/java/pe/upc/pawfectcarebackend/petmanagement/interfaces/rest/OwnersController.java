package pe.upc.pawfectcarebackend.petmanagement.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetAllOwnersQuery;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetOwnerByIdQuery;
import pe.upc.pawfectcarebackend.petmanagement.domain.services.OwnerCommandService;
import pe.upc.pawfectcarebackend.petmanagement.domain.services.OwnerQueryService;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources.CreateOwnerResource;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources.OwnerResource;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources.UpdateOwnerResource;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.transform.CreateOwnerCommandFromResourceAssembler;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.transform.OwnerResourceFromEntityAssembler;
import pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.transform.UpdateOwnerCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/owners", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Owners", description = "Owners Management Endpoints")
@CrossOrigin(origins = {"https://pawfect-care-app-web.web.app","http://localhost:4200"})
public class OwnersController {

    private final OwnerQueryService ownerQueryService;
    private final OwnerCommandService ownerCommandService;
    public OwnersController(OwnerQueryService ownerQueryService,OwnerCommandService ownerCommandService) {
        this.ownerCommandService = ownerCommandService;
        this.ownerQueryService = ownerQueryService;
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerResource> getOwnerById(@PathVariable Long ownerId) {
        var getOwnerByIdQuery = new GetOwnerByIdQuery(ownerId);
        var owner = ownerQueryService.handle(getOwnerByIdQuery);
        if (owner.isEmpty()) return ResponseEntity.badRequest().build();
        var ownerResource = OwnerResourceFromEntityAssembler.toResourceFromEntity(owner.get());
        return ResponseEntity.ok(ownerResource);
    }

    @GetMapping
    public ResponseEntity<List<OwnerResource>> getAllOwners() {
        var getAllOwnersQuery = new GetAllOwnersQuery();
        var owners = ownerQueryService.handle(getAllOwnersQuery);
        var petResources = owners.stream().map(OwnerResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResources);
    }

    @PostMapping
    public ResponseEntity<OwnerResource> createOwner(@RequestBody CreateOwnerResource createOwnerResource) {
        var createOwnerCommand = CreateOwnerCommandFromResourceAssembler.toCommandFromResource(createOwnerResource);
        var ownerId = ownerCommandService.handle(createOwnerCommand);
        if (ownerId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getOwnerByIdQuery = new GetOwnerByIdQuery(ownerId);
        var owner = ownerQueryService.handle(getOwnerByIdQuery);
     if (owner.isEmpty()) return ResponseEntity.badRequest().build();
     var ownerResource = OwnerResourceFromEntityAssembler.toResourceFromEntity(owner.get());
        return new ResponseEntity<>(ownerResource, HttpStatus.CREATED);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerResource> updateOwner(@PathVariable Long ownerId, @RequestBody UpdateOwnerResource updateOwnerResource) {
        var updateOwnerCommand = UpdateOwnerCommandFromResourceAssembler.toCommandFromResource(ownerId, updateOwnerResource);
        var updatedOwner = ownerCommandService.handle(updateOwnerCommand);
        if (updatedOwner.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var ownerResource = OwnerResourceFromEntityAssembler.toResourceFromEntity(updatedOwner.get());
        return ResponseEntity.ok(ownerResource);
    }

}
