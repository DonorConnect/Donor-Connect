package models;

import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;

public class ProjectDAO {

    private static EntityManager entityManager;

    private ProjectDAO() {
        PersistenceProvider persistenceProvider = new HibernatePersistence();
        EntityManagerFactory entityManagerFactory = persistenceProvider.createEntityManagerFactory("NewPersistenceUnit", new HashMap());
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static ProjectDAO getInstance() {
        return new ProjectDAO();
    }

    public void save(Project project) {
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Project fetch(long id) {
        entityManager.getTransaction().begin();
        Project project_found = entityManager.find(Project.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return project_found;
    }
}
