package pe.upc.pawfectcarebackend.iam.interfaces.rest.transform;


import pe.upc.pawfectcarebackend.iam.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.resources.AuthenticatedUserAdminResource;

public class AuthenticatedUserAdminResourceFromEntityAssembler {
    public static AuthenticatedUserAdminResource toResourceFromEntity(UserAdmin userAdmin, String token) {
        return new AuthenticatedUserAdminResource(userAdmin.getId(), userAdmin.getUsername(), token);
    }
}
