package pe.upc.pawfectcarebackend.iam.domain.services;

import pe.upc.pawfectcarebackend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
