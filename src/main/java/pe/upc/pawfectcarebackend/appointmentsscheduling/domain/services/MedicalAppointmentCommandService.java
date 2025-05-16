package pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services;

import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.CreateMedicalAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.UpdateMedicalAppointmentCommand;


import java.util.Optional;

public interface MedicalAppointmentCommandService {
    Long handle(CreateMedicalAppointmentCommand command);
    Optional<MedicalAppointment> handle(UpdateMedicalAppointmentCommand command);
}
