package pe.upc.pawfectcarebackend.petmanagement.domain.services;

import java.util.List;
import java.util.Optional;

import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetAllPetsQuery;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetPetByIdQuery;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetAllPetsByOwnerIdQuery;

public interface PetQueryService {
   Optional<Pet> handle(GetPetByIdQuery query);
   List<Pet> handle(GetAllPetsQuery query);
   List<Pet> handle(GetAllPetsByOwnerIdQuery query);
}
