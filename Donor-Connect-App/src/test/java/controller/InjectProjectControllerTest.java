package controller;

import models.Project;
import models.ProjectDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InjectProjectControllerTest {
    private MockHttpServletResponse response;
    private MockHttpServletRequest request;
    private AnnotationMethodHandlerAdapter adapter;
    private InjectProjectController controller;
    private Project dummyProject;
    private models.ProjectDAO projectDAO;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        adapter = new AnnotationMethodHandlerAdapter();
        dummyProject = new Project("project_name", "project_desc", "img");

        request.setRequestURI("/inject_project.ftl");
        request.setMethod("POST");
        request.setParameter("name", "project_name");
        request.setParameter("description", "project_desc");
        request.setParameter("img", "img");

        controller = new InjectProjectController();
        projectDAO = mock(ProjectDAOImpl.class);
        controller.setDao(projectDAO);
    }

    @Test
    public void shouldInjectProject() throws Exception {
        adapter.handle(request, response, controller);
        verify(projectDAO).save(dummyProject);
    }

    @Test
    public void shouldNotInjectProjectForGetMethod() throws Exception {
        request.setMethod("GET");
        adapter.handle(request, response, controller);
        verify(projectDAO, Mockito.times(0)).save(dummyProject);
    }
}
