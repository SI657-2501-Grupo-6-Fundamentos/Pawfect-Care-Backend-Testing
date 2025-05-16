package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform;

import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.MedicalAppointmentResource;

public class MedicalAppointmentResourceFromEntityAssembler {

    public static MedicalAppointmentResource toResourceFromEntity(MedicalAppointment entity) {
        return new MedicalAppointmentResource(
                entity.getId(),
                entity.getDiagnosis(),
                entity.getNotes(),
                entity.getTreatment(),
                entity.getAppointment().getId());
    }
}
