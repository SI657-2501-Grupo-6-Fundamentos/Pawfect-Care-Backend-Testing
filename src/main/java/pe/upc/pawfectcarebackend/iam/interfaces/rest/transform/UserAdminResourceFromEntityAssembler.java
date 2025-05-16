package pe.upc.pawfectcarebackend.iam.interfaces.rest.transform;

import pe.upc.pawfectcarebackend.iam.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcarebackend.iam.domain.model.entities.Role;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.resources.UserAdminResource;

public class UserAdminResourceFromEntityAssembler {
    public static UserAdminResource toResourceFromEntity(UserAdmin userAdmin) {
        var roles = userAdmin.getRoles().stream().map(Role::getStringName).toList();
        return new UserAdminResource(userAdmin.getId(), userAdmin.getUsername(), roles);
    }
}
