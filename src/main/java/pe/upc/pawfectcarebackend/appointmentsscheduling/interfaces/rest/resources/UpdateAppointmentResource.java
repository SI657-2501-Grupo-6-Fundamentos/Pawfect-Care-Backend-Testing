package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources;

import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.valueobjects.AppointmentStatus;

import java.time.LocalDateTime;


public record UpdateAppointmentResource(
                                        String appointmentName,
                                        LocalDateTime registrationDate,
                                        LocalDateTime endDate,
                                        boolean isMedical,
                                        AppointmentStatus status
) {
}
