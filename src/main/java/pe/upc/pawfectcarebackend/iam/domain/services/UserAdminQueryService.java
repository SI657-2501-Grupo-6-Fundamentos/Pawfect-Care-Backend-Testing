package pe.upc.pawfectcarebackend.iam.domain.services;

import pe.upc.pawfectcarebackend.iam.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcarebackend.iam.domain.model.queries.GetAllUsersAdminQuery;
import pe.upc.pawfectcarebackend.iam.domain.model.queries.GetUserAdminByIdQuery;
import pe.upc.pawfectcarebackend.iam.domain.model.queries.GetUserAdminByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserAdminQueryService {
    List<UserAdmin> handle(GetAllUsersAdminQuery query);
    Optional<UserAdmin> handle(GetUserAdminByIdQuery query);
    Optional<UserAdmin> handle(GetUserAdminByUsernameQuery query);

}
