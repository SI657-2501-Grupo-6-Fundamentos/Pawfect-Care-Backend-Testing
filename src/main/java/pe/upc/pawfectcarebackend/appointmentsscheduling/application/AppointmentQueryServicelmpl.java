package pe.upc.pawfectcarebackend.appointmentsscheduling.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcarebackend.appointmentsscheduling.application.acl.ExternalPetService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.Appointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetAllAppointmentsQuery;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetAppointmentByIdQuery;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.AppointmentQueryService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServicelmpl implements AppointmentQueryService{
    private final AppointmentRepository appointmentRepository;
    public AppointmentQueryServicelmpl(AppointmentRepository appointmentRepository, ExternalPetService externalPetService) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }

}
