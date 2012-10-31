package models;


import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static org.mockito.Mockito.*;

public class ProjectDAOImplTest {
    private Project project;
    private ProjectDAOImpl projectDAO;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        project = new Project("My Project", "description", "image/path");
        entityManager = mock(EntityManager.class);
        when(entityManager.getTransaction()).thenReturn(mock(EntityTransaction.class));
        projectDAO = new ProjectDAOImpl(entityManager);
    }

    @Test
    public void shouldSaveAProject() {
        when(entityManager.merge(project)).thenReturn(project);

        projectDAO.save(project);
        verify(entityManager).merge(project);
        verify(entityManager).flush();
    }


    @Test
    public void testFetchRetrieve() {
        projectDAO.fetch(project.getId());
        verify(entityManager).find(Project.class, project.getId());
    }

    @Test
    public void shouldFetchAllProjects() throws Exception {
        Query allProjectsQuery = mock(Query.class);
        when(entityManager.createQuery("From Project")).thenReturn(allProjectsQuery);

        projectDAO.fetchAllCurrent();

        verify(entityManager).createQuery("From Project");
        verify(allProjectsQuery).getResultList();
    }

    @Test
    public void shouldDeleteAllProjects() throws Exception {
        Query deleteAllProjectQuery = mock(Query.class);
        EntityTransaction mockTransaction = mock(EntityTransaction.class);

        when(entityManager.createQuery("Delete From Project")).thenReturn(deleteAllProjectQuery);
        when(entityManager.getTransaction()).thenReturn(mockTransaction);

        projectDAO.deleteAll();

        verify(entityManager).createQuery("Delete From Project");
        verify(deleteAllProjectQuery).executeUpdate();
    }
}
