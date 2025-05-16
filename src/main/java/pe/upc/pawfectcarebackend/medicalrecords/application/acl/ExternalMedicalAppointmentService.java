package pe.upc.pawfectcarebackend.medicalrecords.application.acl;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.acl.MedicalAppointmentContextFacade;

import java.util.Optional;


@Service
public class ExternalMedicalAppointmentService {
    private final MedicalAppointmentContextFacade medicalAppointmentContextFacade;
    public ExternalMedicalAppointmentService(MedicalAppointmentContextFacade medicalAppointmentContextFacade) {
        this.medicalAppointmentContextFacade = medicalAppointmentContextFacade;
    }
    public Optional<MedicalAppointment> fetchMedicalAppointmentById(Long medicalAppointmentId) {
        var medicalAppointment = medicalAppointmentContextFacade.fetchMedicalAppointmentById(medicalAppointmentId);
        return medicalAppointment;
    }
}
