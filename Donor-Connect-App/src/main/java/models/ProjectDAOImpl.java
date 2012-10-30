package models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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
    public Project save(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
            return project;
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
    public List<Project> fetchAllCurrent() {
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

    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{

            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query deleteQuery = entityManager.createQuery("Delete From Project");
            deleteQuery.executeUpdate();
            entityTransaction.commit();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
