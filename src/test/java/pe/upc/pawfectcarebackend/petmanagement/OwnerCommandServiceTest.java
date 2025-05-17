package pe.upc.pawfectcarebackend.petmanagement;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.upc.pawfectcarebackend.petmanagement.application.OwnerCommandServiceImpl;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Owner;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.commands.CreateOwnerCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.services.OwnerCommandService;
import pe.upc.pawfectcarebackend.petmanagement.infrastructure.persistence.jpa.repositories.OwnerRepository;
import pe.upc.pawfectcarebackend.petmanagement.infrastructure.persistence.jpa.repositories.PetRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OwnerCommandServiceTest {

    /**
     * Test for handleCreateOwnerCommand method
     */
    @Test
    void handleCreateOwnerCommand() {
        /*
          Arrange
          Mock the dependencies
         */
        OwnerRepository ownerRepository = Mockito.mock(OwnerRepository.class);
        PetRepository petRepository = Mockito.mock(PetRepository.class);

        // Create an instance of the OwnerCommandService
        OwnerCommandService ownerCommandService = new OwnerCommandServiceImpl(ownerRepository, petRepository);

        // Create the command to add an owner
        CreateOwnerCommand command = new CreateOwnerCommand("John Doe", "john.doe@example.com", "123456789", "123 Main St");
        when(ownerRepository.existsByEmail(command.email())).thenReturn(false);
        when(ownerRepository.existsById(anyLong())).thenReturn(false);
        Owner mockOwner = new Owner(command);
        when(ownerRepository.save(any(Owner.class))).thenReturn(mockOwner);

        /*
          Act
          Call the method to be tested
         */
        Long ownerId = ownerCommandService.handle(command);

        // Debugging: Print the created owner details
        System.out.println("\nCreated Owner:\n------------------------------\n");
        System.out.println("Created Owner Name: " + mockOwner.getFullName());
        System.out.println("Created Owner Email: " + mockOwner.getEmail());
        System.out.println("Created Owner Phone: " + mockOwner.getPhoneNumber());
        System.out.println("Created Owner Address: " + mockOwner.getAddress());
        System.out.println("------------------------------\n");

        /*
          Assert
          Verify the expected behavior
         */
        assertEquals(mockOwner.getId(), ownerId);
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }
}