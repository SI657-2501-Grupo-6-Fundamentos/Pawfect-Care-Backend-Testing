package pe.upc.pawfectcarebackend.petmanagement.domain.model.commands;

public record CreateOwnerCommand(
        String fullName,
        String phoneNumber,
        String email,
        String address
) {

}
