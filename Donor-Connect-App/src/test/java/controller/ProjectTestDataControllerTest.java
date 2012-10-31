package controller;

import models.Project;
import models.ProjectDAOImpl;
import models.ProjectStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProjectTestDataControllerTest {
    private MockHttpServletResponse response;
    private MockHttpServletRequest request;
    private AnnotationMethodHandlerAdapter adapter;
    private ProjectTestDataController testDataController;
    private Project dummyProject;
    private models.ProjectDAO projectDAO;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        adapter = new AnnotationMethodHandlerAdapter();
        dummyProject = new Project(100,"project_name", "project_desc", "img", ProjectStatus.CURRENT,"image/path","summary");

        request.setRequestURI("/inject_project.ftl");
        request.setMethod("POST");
        request.setParameter("name", "project_name");
        request.setParameter("description", "project_desc");
        request.setParameter("img", "img");
        request.setParameter("status","CURRENT");
        request.setParameter("thumbnail","thumbnail");
        request.setParameter("summary","summary");

        testDataController = new ProjectTestDataController();
        projectDAO = mock(ProjectDAOImpl.class);
        testDataController.setDao(projectDAO);
    }

    @Test
    public void shouldInjectProject() throws Exception {
        when(projectDAO.save(dummyProject)).thenReturn(dummyProject);

        ModelAndView modelAndView = adapter.handle(request, response, testDataController);

        verify(projectDAO).save(dummyProject);
        assertThat(modelAndView.getViewName(), is("inject_project"));
        assertThat((String) modelAndView.getModel().get("created_project_id"), is("100"));
    }

    @Test
    public void shouldNotInjectProjectForGetMethod() throws Exception {
        request.setMethod("GET");
        adapter.handle(request, response, testDataController);
        verify(projectDAO, Mockito.times(0)).save(dummyProject);
    }

    @Test
    public void shouldDeleteAllProjects() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/delete_project.ftl");
        request.setMethod("GET");
        adapter.handle(request, response, testDataController);
        verify(projectDAO).deleteAll();
    }
}