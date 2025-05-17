package pe.upc.pawfectcarebackend.petmanagement.interfaces.rest.resources;

public record OwnerResource(
        Long id,
        String fullName,
        String phoneNumber,
        String email,
        String address) {
}
