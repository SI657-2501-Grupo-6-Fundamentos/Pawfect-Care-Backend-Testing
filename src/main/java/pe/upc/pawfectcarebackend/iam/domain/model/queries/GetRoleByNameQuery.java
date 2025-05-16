package pe.upc.pawfectcarebackend.iam.domain.model.queries;

import pe.upc.pawfectcarebackend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
