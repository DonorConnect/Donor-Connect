package models;


import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.mockito.Mockito.*;

public class ProjectDAOImplTest {
    private Project project;
    private ProjectDAOImpl projectDAO;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        project = new Project("My Project", "description", "image/path");
        EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);
        entityManager = mock(EntityManager.class);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        projectDAO = new ProjectDAOImpl(entityManagerFactory);
    }

    @Test
    public void shouldSaveAProject() {
        projectDAO.save(project);
        verify(entityManager).persist(project);
        verify(entityManager).close();
    }


    @Test
    public void testFetchRetrieve() {
        projectDAO.fetch(project.getId());
        verify(entityManager).find(Project.class, project.getId());
        verify(entityManager).close();
    }


}
