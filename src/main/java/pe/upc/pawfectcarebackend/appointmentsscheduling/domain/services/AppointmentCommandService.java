package pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services;

import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.Appointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.UpdateAppointmentCommand;


import java.util.Optional;

public interface AppointmentCommandService {
    Optional<Appointment> handle(UpdateAppointmentCommand command);
    Long handle(CreateAppointmentCommand command);
}
