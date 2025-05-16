package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform;


import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.UpdateMedicalAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.UpdateMedicalAppointmentResource;

public class UpdateMedicalAppointmentCommandFromResourceAssembler {
    public static UpdateMedicalAppointmentCommand toCommandFromResource(
            Long medicalAppointmentId,
            UpdateMedicalAppointmentResource resource) {
        return new UpdateMedicalAppointmentCommand(
                medicalAppointmentId,
                resource.diagnosis(),
                resource.notes(),
                resource.treatment());
    }
}
