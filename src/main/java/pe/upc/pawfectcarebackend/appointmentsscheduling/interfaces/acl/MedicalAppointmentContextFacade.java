package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.acl;


import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetMedicalAppointmentByIdQuery;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.MedicalAppointmentQueryService;

import java.util.Optional;

@Service
public class MedicalAppointmentContextFacade {
    private final MedicalAppointmentQueryService medicalAppointmentQueryService;
    public MedicalAppointmentContextFacade(
            MedicalAppointmentQueryService medicalAppointmentQueryService
    ){
        this.medicalAppointmentQueryService = medicalAppointmentQueryService;
    }

    public Optional<MedicalAppointment>  fetchMedicalAppointmentById(Long medicalAppointmentId) {
        var getMedicalAppointmentByIdQuery = new GetMedicalAppointmentByIdQuery(medicalAppointmentId);
        var medicalAppointment = medicalAppointmentQueryService.handle(getMedicalAppointmentByIdQuery);
        return medicalAppointment;
    }

}
