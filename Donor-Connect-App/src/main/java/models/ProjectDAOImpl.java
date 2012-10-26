package models;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Repository("projectDAO")
public class ProjectDAOImpl implements ProjectDAO {
    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @Transactional
    public void save(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.persist(project);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Project fetch(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Project.class, id);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
