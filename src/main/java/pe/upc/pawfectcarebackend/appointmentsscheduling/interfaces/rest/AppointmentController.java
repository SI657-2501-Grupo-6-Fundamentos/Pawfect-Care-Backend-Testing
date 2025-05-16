package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.CreateMedicalAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetAllAppointmentsQuery;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetAppointmentByIdQuery;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.AppointmentCommandService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.AppointmentQueryService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.MedicalAppointmentCommandService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.AppointmentResource;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.CreateAppointmentResource;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.UpdateAppointmentResource;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform.UpdateAppointmentCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Appointment", description = "Appointment Management Endpoints")
@CrossOrigin(origins = {"https://pawfect-care-app-web.web.app","http://localhost:4200"})
public class AppointmentController {

    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;
    private final MedicalAppointmentCommandService medicalAppointmentCommandService;
    public AppointmentController(
            AppointmentQueryService appointmentQueryService,
            AppointmentCommandService appointmentCommandService,
            MedicalAppointmentCommandService medicalAppointmentCommandService
    ) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
        this.medicalAppointmentCommandService=medicalAppointmentCommandService;
    }
    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource createAppointmentResource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(createAppointmentResource);
        var appointmentId = appointmentCommandService.handle(createAppointmentCommand);
        if (appointmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }

      var getAppointmentById = new GetAppointmentByIdQuery(appointmentId);
       var appointment = appointmentQueryService.handle(getAppointmentById);
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();

        if(appointment.get().isMedical()) {
            var createMedicalAppointmentCommand = new CreateMedicalAppointmentCommand(
                    "Initial diagnosis",
                    "Initial treatment",
                    "Initial notes",
                    appointmentId,
                    appointment.get().getPet().getMedicalHistory().getId()
            );
            var medicalAppointmentId = medicalAppointmentCommandService.handle(createMedicalAppointmentCommand);
            if (medicalAppointmentId == 0L) return ResponseEntity.badRequest().build();

        }

        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long appointmentId) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAllAppointments() {
        var getAllAppointmentsQuery = new GetAllAppointmentsQuery();
        var appointments = appointmentQueryService.handle(getAllAppointmentsQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResources);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResource> updateAppointment(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentResource updateAppointmentResource) {
        var updateAppointmentCommand = UpdateAppointmentCommandFromResourceAssembler.toCommandFromResource(appointmentId, updateAppointmentResource);
        var updatedAppointment = appointmentCommandService.handle(updateAppointmentCommand);
        if (updatedAppointment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(updatedAppointment.get());
        return ResponseEntity.ok(appointmentResource);
    }





}