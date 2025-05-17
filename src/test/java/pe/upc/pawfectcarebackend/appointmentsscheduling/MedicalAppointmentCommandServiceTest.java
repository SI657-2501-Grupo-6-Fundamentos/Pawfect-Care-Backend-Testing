package pe.upc.pawfectcarebackend.appointmentsscheduling;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.upc.pawfectcarebackend.appointmentsscheduling.application.MedicalAppointmentCommandServiceImpl;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.Appointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.CreateMedicalAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import pe.upc.pawfectcarebackend.appointmentsscheduling.infrastructure.persistence.jpa.repositories.MedicalAppointmentRepository;
import pe.upc.pawfectcarebackend.petmanagement.application.acl.ExternalMedicalHistoryService;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Pet;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MedicalAppointmentCommandServiceTest {

    @Test
    void handleCreateMedicalAppointmentCommand() {
        /*
          Arrange
          Mock the dependencies
         */
        MedicalAppointmentRepository medicalAppointmentRepository = Mockito.mock(MedicalAppointmentRepository.class);
        AppointmentRepository appointmentRepository = Mockito.mock(AppointmentRepository.class);
        ExternalMedicalHistoryService externalMedicalHistoryService = Mockito.mock(ExternalMedicalHistoryService.class);

        // Create an instance of the MedicalAppointmentCommandService
        MedicalAppointmentCommandServiceImpl medicalAppointmentCommandService = new MedicalAppointmentCommandServiceImpl(
                medicalAppointmentRepository,
                appointmentRepository,
                externalMedicalHistoryService
        );

        // Simulate the existing Appointment and Pet
        MedicalHistory mockMedicalHistory = Mockito.mock(MedicalHistory.class);
        when(mockMedicalHistory.getId()).thenReturn(1L);

        Pet mockPet = Mockito.mock(Pet.class);
        when(mockPet.getMedicalHistory()).thenReturn(mockMedicalHistory);

        Appointment mockAppointment = Mockito.mock(Appointment.class);
        when(mockAppointment.getPet()).thenReturn(mockPet);
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(mockAppointment));

        // Create the command to add a medical appointment
        CreateMedicalAppointmentCommand command = new CreateMedicalAppointmentCommand(
                "diagnosis",
                "Treatment Example",
                "Notes Example",
                1L,
                1L
        );

        // Simulate the behavior of MedicalAppointmentRepository
        MedicalAppointment mockMedicalAppointment = new MedicalAppointment(
                command.diagnosis(),
                command.treatment(),
                command.notes()
        );
        mockMedicalAppointment.setMedicalHistory(mockMedicalHistory);
        mockMedicalAppointment.setAppointment(mockAppointment);
        when(medicalAppointmentRepository.save(any(MedicalAppointment.class))).thenReturn(mockMedicalAppointment);

        /*
          Act
          Call the method to be tested
         */
        Long medicalAppointmentId = medicalAppointmentCommandService.handle(command);

        // Debugging: Print the created medical appointment details
        System.out.println("\nCreated Medical Appointment: \n------------------------------\n");
        System.out.println("Diagnosis: " + mockMedicalAppointment.getDiagnosis());
        System.out.println("Treatment: " + mockMedicalAppointment.getTreatment());
        System.out.println("Notes: " + mockMedicalAppointment.getNotes());
        System.out.println("Appointment ID: " + mockMedicalAppointment.getAppointment().getId());
        System.out.println("Medical History ID: " + mockMedicalAppointment.getMedicalHistory().getId());
        System.out.println("------------------------------\n");

        /*
          Assert
          Verify the results
         */
        assertEquals(mockMedicalAppointment.getId(), medicalAppointmentId);
        verify(appointmentRepository, times(1)).findById(command.appointmentId());
        verify(medicalAppointmentRepository, times(1)).save(any(MedicalAppointment.class));
        verify(externalMedicalHistoryService, times(1)).AddMedicalAppointmentToMedicalHistory(
                mockMedicalHistory.getId(),
                mockMedicalAppointment.getId()
        );
    }
}