package pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.commands.CreateAppointmentCommand;
import pe.upc.pawfectcarebackend.appointmentsscheduling.domain.model.valueobjects.AppointmentStatus;
import pe.upc.pawfectcarebackend.medicalrecords.domain.model.aggregates.MedicalHistory;
import pe.upc.pawfectcarebackend.petmanagement.domain.model.aggregates.Pet;
import pe.upc.pawfectcarebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;


@Getter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    private String appointmentName;
    private LocalDateTime registrationDate;
    private LocalDateTime endDate;
    private boolean isMedical;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "pet_id")
    @NotNull
    private Pet pet;

    @Setter
    @OneToOne(mappedBy = "appointment")
    private MedicalAppointment medicalAppointment;



    public Appointment() {
        this.appointmentName = Strings.EMPTY;
        this.registrationDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
        this.isMedical = false;
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Appointment(CreateAppointmentCommand command) {
        this();
        this.appointmentName = command.appointmentName();
        this.registrationDate = command.registrationDate();
        this.endDate = command.endDate();
        this.isMedical = command.isMedical();
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Appointment updateInformation(String appointmentName,LocalDateTime registrationDate,LocalDateTime endDate, boolean isMedical,AppointmentStatus status ) {
        this.appointmentName = appointmentName;
        this.registrationDate = registrationDate;
        this.endDate = endDate;
        this.isMedical = isMedical;
        this.status = status;
        return this;
    }

}
