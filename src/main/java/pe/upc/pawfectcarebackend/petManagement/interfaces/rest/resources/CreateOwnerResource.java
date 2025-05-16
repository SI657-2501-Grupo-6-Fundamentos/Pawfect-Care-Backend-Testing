package pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources;

public record CreateOwnerResource(
        String fullName,
        String phoneNumber,
        String email,
        String address) {
}
