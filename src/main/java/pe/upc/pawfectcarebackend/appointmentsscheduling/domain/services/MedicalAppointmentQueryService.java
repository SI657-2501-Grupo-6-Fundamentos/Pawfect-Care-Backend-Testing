package pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services;

import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetAllMedicalAppointmentsByMedicalHistoryId;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetMedicalAppointmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MedicalAppointmentQueryService {
    Optional<MedicalAppointment> handle(GetMedicalAppointmentByIdQuery query);
    List<MedicalAppointment> handle(GetAllMedicalAppointmentsByMedicalHistoryId query);
}
