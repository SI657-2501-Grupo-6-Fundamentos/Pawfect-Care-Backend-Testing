package pe.upc.pawfectcarebackend.iam.domain.services;

import pe.upc.pawfectcarebackend.iam.domain.model.aggregates.UserAdmin;
import pe.upc.pawfectcarebackend.iam.domain.model.commands.SignInCommand;
import pe.upc.pawfectcarebackend.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserAdminCommandService {
    Optional<ImmutablePair<UserAdmin, String>> handle(SignInCommand command);
    Optional<UserAdmin> handle(SignUpCommand command);


}
