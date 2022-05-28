package hospital.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "prescriptions_id"),
        inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private Set<Medication> medications;

    @OneToOne(mappedBy = "prescription")
    private Visit visit;

    protected Prescription() {
    }

    public Prescription(Visit visit) {
       setVisit(visit);
       this.medications = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }


    private Visit getVisit() {
        return visit;
    }

    private void setVisit(Visit visit) {
        if(visit == null) {
            throw new IllegalArgumentException("Visit must be valid!");
        }
        this.visit = visit;
    }

    public void addMedication(Medication medication) {
        if(medication == null) {
            throw new IllegalArgumentException("Medication must not be Null!");
        }

        medications.add(medication);
    }
}
