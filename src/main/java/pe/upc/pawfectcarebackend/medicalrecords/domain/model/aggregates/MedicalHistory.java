package pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.Appointment;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.commands.CreateMedicalHistoryCommand;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcarebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class MedicalHistory extends AuditableAbstractAggregateRoot<MedicalHistory> {


    @NotBlank
    private String notes;

    @Setter
    @OneToOne(mappedBy = "medicalHistory")
    private Pet pet;


    @Setter
    @OneToMany(mappedBy = "medicalHistory", cascade = CascadeType.ALL)
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();

    public MedicalHistory(){

    }

    public MedicalHistory(CreateMedicalHistoryCommand command){
        this.notes = command.notes();
    }

    public MedicalHistory addMedicalAppointmentToMedicalHistory(MedicalAppointment medicalAppointment) {
        System.out.println("Adding medical appointment to MedicalHistory");
        this.medicalAppointments.add(medicalAppointment);
        return this;
    }
}
