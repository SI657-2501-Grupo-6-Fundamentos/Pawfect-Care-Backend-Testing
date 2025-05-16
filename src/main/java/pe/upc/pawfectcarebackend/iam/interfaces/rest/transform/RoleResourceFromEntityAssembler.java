package pe.upc.pawfectcarebackend.iam.interfaces.rest.transform;

import pe.upc.pawfectcarebackend.iam.domain.model.entities.Role;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
