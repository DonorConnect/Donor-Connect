package models;


import org.hibernate.ejb.HibernatePersistence;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.swing.*;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class ProjectTest {
    private EntityManager entityManager;
    private Project project = new Project("My Project", "description");

    @Before
    public void setUp() throws Exception {
        PersistenceProvider persistenceProvider = new HibernatePersistence();
        EntityManagerFactory entityManagerFactory = persistenceProvider.createEntityManagerFactory("NewPersistenceUnit", new HashMap());
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testStoreRetrieve() {
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
    @Test
    public void testFetchRetrieve() {
        entityManager.getTransaction().begin();
        Project project_expect = entityManager.find(Project.class, (long)1);
        entityManager.getTransaction().commit();
        entityManager.close();

        assertEquals(project, project_expect);

    }

}
