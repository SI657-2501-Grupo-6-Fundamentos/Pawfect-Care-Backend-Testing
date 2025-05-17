package pe.upc.pawfectcarebackend.petmanagement.domain.services;

import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Owner;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetAllOwnersQuery;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.queries.GetOwnerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface OwnerQueryService {
  List<Owner> handle(GetAllOwnersQuery query);
  Optional<Owner> handle(GetOwnerByIdQuery query);
}
