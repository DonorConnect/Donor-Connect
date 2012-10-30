package models;


import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static org.mockito.Mockito.*;

public class ProjectDAOImplTest {
    private Project project;
    private ProjectDAOImpl projectDAO;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        project = new Project("My Project", "description", "image/path",true,"image/path","summary");
        EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);
        entityManager = mock(EntityManager.class);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(mock(EntityTransaction.class));
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

    @Test
    public void shouldFetchAllCurrentProjects() throws Exception {
        Query allProjectsQuery = mock(Query.class);
        when(entityManager.createQuery("From Project p where p.status = true")).thenReturn(allProjectsQuery);

        projectDAO.fetchAllCurrent();

        verify(entityManager).createQuery("From Project p where p.status = true");
        verify(allProjectsQuery).getResultList();
        verify(entityManager).close();
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
        verify(mockTransaction).begin();
        verify(mockTransaction).commit();
    }
}
