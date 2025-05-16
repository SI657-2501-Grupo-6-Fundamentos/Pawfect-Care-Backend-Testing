package pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetAllAppointmentsQuery;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.queries.GetAllMedicalAppointmentsByMedicalHistoryId;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.AppointmentCommandService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.AppointmentQueryService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.MedicalAppointmentCommandService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.services.MedicalAppointmentQueryService;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.AppointmentResource;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.MedicalAppointmentResource;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.resources.UpdateMedicalAppointmentResource;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform.MedicalAppointmentResourceFromEntityAssembler;
import pe.upc.pawfectcarebackend.appointmentsscheduling.interfaces.rest.transform.UpdateMedicalAppointmentCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/medical_appointments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Medical Appointment", description = "Medical Appointment Management Endpoints")
@CrossOrigin(origins = {"https://pawfect-care-app-web.web.app","http://localhost:4200"})

public class MedicalAppointmentController {

    private final MedicalAppointmentQueryService medicalAppointmentQueryService;
    private final MedicalAppointmentCommandService medicalAppointmentCommandService;
    public MedicalAppointmentController(
            MedicalAppointmentQueryService medicalAppointmentQueryService,
            MedicalAppointmentCommandService medicalAppointmentCommandService
    ) {
        this.medicalAppointmentQueryService = medicalAppointmentQueryService;
        this.medicalAppointmentCommandService=medicalAppointmentCommandService;

    }


    @GetMapping("/medical_history/{medicalHistoryId}")
    public ResponseEntity<List<MedicalAppointmentResource>> getAllMedicalAppointmentsByMedicalHistoryId(@PathVariable Long medicalHistoryId) {
        var getAllMedicalAppointmentsByMedicalHistoryIdQuery = new GetAllMedicalAppointmentsByMedicalHistoryId(medicalHistoryId);
        var medicalAppointments = medicalAppointmentQueryService.handle(getAllMedicalAppointmentsByMedicalHistoryIdQuery);
        var medicalAppointmentResources = medicalAppointments.stream().map(MedicalAppointmentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(medicalAppointmentResources);
    }

    @PutMapping("/{medicalAppointmentId}")
    public ResponseEntity<MedicalAppointmentResource> updateMedicalAppointment(@PathVariable Long medicalAppointmentId, @RequestBody UpdateMedicalAppointmentResource updateMedicalAppointmentResource) {
        var updateMedicalAppointmentCommand = UpdateMedicalAppointmentCommandFromResourceAssembler.toCommandFromResource(medicalAppointmentId, updateMedicalAppointmentResource);
        var updatedMedicalAppointment = medicalAppointmentCommandService.handle(updateMedicalAppointmentCommand);
        if (updatedMedicalAppointment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var medicalAppointmentResource = MedicalAppointmentResourceFromEntityAssembler.toResourceFromEntity(updatedMedicalAppointment.get());
        return ResponseEntity.ok(medicalAppointmentResource);
    }



}
