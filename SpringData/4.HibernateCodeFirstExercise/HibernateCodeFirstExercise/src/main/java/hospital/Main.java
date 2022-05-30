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
                "Dosko",
                "Poskov",
                "Plovdiv",
                "Dos@Poskov.com",
                LocalDate.of(1991,01,11),
                true);
        entityManager.persist(patient);
        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication("Hen", 10));
        medications.add(new Medication("Ban", 15));
        medications.add(new Medication("Gassa", 3.5));
        medications.forEach(entityManager::persist);
        Diagnose diagnose = new Diagnose("Ashrw");
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
