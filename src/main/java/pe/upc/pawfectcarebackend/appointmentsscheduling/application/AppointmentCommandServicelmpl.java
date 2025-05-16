package pe.upc.pawfectcarebackend.appointmentsscheduling.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.appointmentsscheduling.application.acl.ExternalPetService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.Appointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.UpdateAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.AppointmentCommandService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import pe.upc.pawfectcarebackend.appointmentsscheduling.infrastructure.persistence.jpa.repositories.MedicalAppointmentRepository;
import pe.upc.pawfectcarebackend.petmanagement.application.acl.ExternalMedicalHistoryService;
import pe.upc.pawfectcarebackend.petmanagement.domain.exceptions.PetNotFoundException;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Pet;

import java.util.Optional;

@Service
public class AppointmentCommandServicelmpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;
    private final ExternalPetService externalPetService;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public AppointmentCommandServicelmpl(
            AppointmentRepository appointmentRepository,
            ExternalPetService externalPetService,
            MedicalAppointmentRepository medicalAppointmentRepository

    ) {
        this.appointmentRepository = appointmentRepository;
        this.externalPetService = externalPetService;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    @Override
    public Long handle(CreateAppointmentCommand command) {

        Pet pet = externalPetService.fetchPetById(command.petId()).orElseThrow(() -> new PetNotFoundException(command.petId()));
        Appointment appointment = new Appointment(command);
        appointment.setPet(pet);
        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving appointment: " + e.getMessage());
        }

        return appointment.getId();
    }

    @Override
    public Optional<Appointment> handle(UpdateAppointmentCommand command) {
        if (!appointmentRepository.existsById(command.appointmentId()))
            throw new IllegalArgumentException("appointmentId does not exist");
        var result = appointmentRepository.findById(command.appointmentId());
        var appointmentToUpdate = result.get();
        try {
            var updatedAppointment = appointmentRepository.save(appointmentToUpdate.updateInformation(command.appointmentName(), command.registrationDate(), command.endDate(), command.isMedical(), command.status()));
            return Optional.of(updatedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating appointment: " + e.getMessage());
        }
    }
}
