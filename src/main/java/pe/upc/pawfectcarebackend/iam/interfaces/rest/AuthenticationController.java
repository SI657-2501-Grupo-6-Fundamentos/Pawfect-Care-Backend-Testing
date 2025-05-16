package pe.upc.pawfectcarebackend.iam.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.pawfectcarebackend.iam.domain.services.UserAdminCommandService;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.resources.AuthenticatedUserAdminResource;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.resources.SignInResource;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.resources.SignUpResource;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.resources.UserAdminResource;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.transform.AuthenticatedUserAdminResourceFromEntityAssembler;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import pe.upc.pawfectcarebackend.iam.interfaces.rest.transform.UserAdminResourceFromEntityAssembler;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/sign-in</li>
 *         <li>POST /api/v1/auth/sign-up</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserAdminCommandService userAdminCommandService;

    public AuthenticationController(UserAdminCommandService userAdminCommandService) {
        this.userAdminCommandService = userAdminCommandService;
    }

    /**
     * Handles the sign-in request.
     * @param signInResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserAdminResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userAdminCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserAdminResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Handles the sign-up request.
     * @param signUpResource the sign-up request body.
     * @return the created user resource.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserAdminResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var userAdmin = userAdminCommandService.handle(signUpCommand);
        if (userAdmin.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userAdminResource = UserAdminResourceFromEntityAssembler.toResourceFromEntity(userAdmin.get());
        return new ResponseEntity<>(userAdminResource, HttpStatus.CREATED);

    }
}
