package hospital.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "diagnose_id",nullable = false)
    private Diagnose diagnose;
    @OneToOne
    private Prescription prescription;
    private LocalDate date;
    private String comment;

    @ManyToOne
    private Patient patient;

    protected Visit() {
    }

    public Visit(Diagnose diagnose, Prescription prescription, String comment, Patient patient) {
        this.diagnose = diagnose;
        this.prescription = prescription;
        this.date = LocalDate.now();
        this.comment = comment;
        this.patient = patient;
    }

    //todo: getters and setters with validations
}
