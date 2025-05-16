package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources;

public record UpdateMedicalAppointmentResource(
        String diagnosis,
        String notes,
        String treatment
) {
}
