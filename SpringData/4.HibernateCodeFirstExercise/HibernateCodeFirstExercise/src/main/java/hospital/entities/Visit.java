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

    public Visit(Diagnose diagnose, Prescription prescription, Patient patient) {
        setDiagnose(diagnose);
        setPrescription(prescription);
        this.date = LocalDate.now();
        setPatient(patient);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    private void setDiagnose(Diagnose diagnose) {
        if(diagnose == null) {
            throw new IllegalArgumentException("Diagnose must not be null!");
        }
        this.diagnose = diagnose;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    private void setPrescription(Prescription prescription) {
        if(prescription == null) {
            throw  new IllegalArgumentException("Prescription must not be null!");
        }
        this.prescription = prescription;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        if(date == null || date.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date must be valid!");
        }
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private Patient getPatient() {
        return patient;
    }

    private void setPatient(Patient patient) {
        if(patient == null) {
            throw new IllegalArgumentException("Patient must not be null!");
        }

        this.patient = patient;
    }
}
