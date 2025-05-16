package pe.upc.pawfectcarebackend.iam.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.pawfectcarebackend.iam.domain.model.queries.GetAllUsersAdminQuery;
import pe.upc.pawfectcarebackend.iam.domain.model.queries.GetUserAdminByIdQuery;
import pe.upc.pawfectcarebackend.iam.domain.services.UserAdminQueryService;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.resources.UserAdminResource;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.transform.UserAdminResourceFromEntityAssembler;

import java.util.List;

/**
 * This class is a REST controller that exposes the users resource.
 * It includes the following operations:
 * - GET /api/v1/users: returns all the users
 * - GET /api/v1/users/{userId}: returns the user with the given id
 **/
@RestController
@RequestMapping(value = "/api/v1/users_admin", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users Admin", description = "UserAdmin Management Endpoints")
public class UsersAdminController {
    private final UserAdminQueryService userAdminQueryService;

    public UsersAdminController(UserAdminQueryService userAdminQueryService) {
        this.userAdminQueryService = userAdminQueryService;
    }

    /**
     * This method returns all the users.
     * @return a list of user resources
     * @see UserAdminResource
     */
    @GetMapping
    public ResponseEntity<List<UserAdminResource>> getAllUsersAdmin() {
        var getAllUsersAdminQuery = new GetAllUsersAdminQuery();
        var usersAdmin = userAdminQueryService.handle(getAllUsersAdminQuery);
        var userAdminResources = usersAdmin.stream().map(UserAdminResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userAdminResources);
    }

    /**
     * This method returns the user with the given id.
     * @param userAdminId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see UserAdminResource
     */
    @GetMapping(value = "/{userAdminId}")
    public ResponseEntity<UserAdminResource> getUserAdminById(@PathVariable Long userAdminId) {
        var getUserByIdQuery = new GetUserAdminByIdQuery(userAdminId);
        var userAdmin = userAdminQueryService.handle(getUserByIdQuery);
        if (userAdmin.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userAdminResource = UserAdminResourceFromEntityAssembler.toResourceFromEntity(userAdmin.get());
        return ResponseEntity.ok(userAdminResource);
    }
}
