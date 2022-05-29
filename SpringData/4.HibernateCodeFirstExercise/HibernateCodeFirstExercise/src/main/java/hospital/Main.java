package hospital;

import hospital.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hospital");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Patient patient = new Patient(
                "Gosho",
                "Peshov",
                "Plovdiv",
                "Gosho@Peshev.com",
                LocalDate.of(1994,05,06),
                true);
        entityManager.persist(patient);
        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication("Lorem", 10));
        medications.add(new Medication("Ipsum", 15));
        medications.add(new Medication("Test", 3.5));
        medications.forEach(entityManager::persist);
        Diagnose diagnose = new Diagnose("Covid-23");
        entityManager.persist(diagnose);
        Prescription prescription = new Prescription();
        medications.forEach(prescription::addMedication);
        entityManager.persist(prescription);
        Visit visit = new Visit(diagnose, prescription, patient);
        entityManager.persist(visit);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
