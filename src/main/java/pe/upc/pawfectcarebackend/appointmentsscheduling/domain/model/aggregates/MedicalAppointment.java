package pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
@Getter
@Entity
public class MedicalAppointment extends AuditableAbstractAggregateRoot<MedicalAppointment> {


    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Setter
    @ManyToOne
    @JoinColumn(name = "medical_history_id")
    private MedicalHistory medicalHistory;

    private String diagnosis;
    private String treatment;
    private String notes;



    public MedicalAppointment updateInformation(String diagnosis, String notes, String treatment) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        return this;
    }

    public MedicalAppointment(String diagnosis, String treatment, String notes) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
    }
    public MedicalAppointment() {
    }


}
