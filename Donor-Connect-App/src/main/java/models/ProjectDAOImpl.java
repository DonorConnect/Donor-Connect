package models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository("projectDAO")
public class ProjectDAOImpl implements ProjectDAO {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired(required = true)
    public ProjectDAOImpl(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
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

    @Override
    public List<Project> fetchAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("From Project").getResultList();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
