import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class _09_FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Project> projects = entityManager.createQuery("SELECT p FROM Project p" +
                                " ORDER BY p.startDate DESC",
                        Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream().sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (Project project : projects) {
            sb.append(projectFormatter(project));
        }

        System.out.println(sb);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static String projectFormatter(Project project) {
        String spacer = "    ";
        return String.format("Project name: %s\n" +
                        "%sProject Description: %s\n" +
                        "%sProject Start Date: %s\n" +
                        "%sProject End Date: %s\n",
                project.getName(),
                spacer, project.getDescription(),
                spacer, project.getStartDate(),
                spacer, project.getEndDate());
    }
}
