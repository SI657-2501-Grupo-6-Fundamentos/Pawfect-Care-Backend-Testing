package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform;

import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.UpdateAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.UpdateAppointmentResource;

public class UpdateAppointmentCommandFromResourceAssembler {
    public static UpdateAppointmentCommand toCommandFromResource(
            Long appointmentId,
            UpdateAppointmentResource resource) {
        return new UpdateAppointmentCommand(
                appointmentId,
                resource.appointmentName(),
                resource.registrationDate(),
                resource.endDate(),
                resource.isMedical(),
                resource.status());
    }
}
