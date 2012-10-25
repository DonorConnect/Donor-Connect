package models;

import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;

public class ProjectDAOTmp {

    private static EntityManager entityManager;

    private ProjectDAOTmp() {
        PersistenceProvider persistenceProvider = new HibernatePersistence();
        EntityManagerFactory entityManagerFactory = persistenceProvider.createEntityManagerFactory("NewPersistenceUnit", new HashMap());
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static ProjectDAOTmp getInstance() {
        return new ProjectDAOTmp();
    }

    public void save(Project project) {
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.getTransaction().commit();
//        entityManager.close();
    }

    public Project fetch(long id) {
        entityManager.getTransaction().begin();
        Project project_found = entityManager.find(Project.class, id);
        entityManager.getTransaction().commit();
//        entityManager.close();
        return project_found;
    }

    public void update(Project project){
        entityManager.getTransaction().begin();
        entityManager.refresh(project);
        entityManager.getTransaction().commit();
    }


}
