package pe.upc.pawfectcarebackend.appointmentsscheduling.application;


import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetAllMedicalAppointmentsByMedicalHistoryId;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetMedicalAppointmentByIdQuery;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.MedicalAppointmentQueryService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.infrastructure.persistence.jpa.repositories.MedicalAppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalAppointmentQueryServiceImpl  implements MedicalAppointmentQueryService {
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentQueryServiceImpl(
            MedicalAppointmentRepository medicalAppointmentRepository
    ) {
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    @Override
    public Optional<MedicalAppointment> handle(GetMedicalAppointmentByIdQuery query) {
        return medicalAppointmentRepository.findById(query.medicalAppointmentId());
    }

    @Override
    public List<MedicalAppointment> handle(GetAllMedicalAppointmentsByMedicalHistoryId query) {
        var medicalAppointments = medicalAppointmentRepository.findAllByMedicalHistoryId(query.medicalHistoryId());
        System.out.println("medicalAppointments: " + medicalAppointments+query.medicalHistoryId());
        return medicalAppointments;
    }


}
