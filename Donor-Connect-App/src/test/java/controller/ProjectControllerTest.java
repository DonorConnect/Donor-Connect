package controller;

import models.Project;
import models.ProjectDAOImpl;
import models.ProjectStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProjectControllerTest {

    private MockHttpServletResponse response;
    private MockHttpServletRequest request;
    private AnnotationMethodHandlerAdapter adapter;
    private ProjectController controller;
    private Project dummyProject;
    private models.ProjectDAO projectDAO;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        adapter = new AnnotationMethodHandlerAdapter();
        Date creationDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 10);
        Date endDate = calendar.getTime();
        dummyProject = new Project(0, "name", creationDate, endDate, 500.0, ProjectStatus.CURRENT, "summary", "description", "image/path", "image/path", 655);

        request.setRequestURI("/project");
        request.setMethod("GET");
        request.setParameter("id", "1");

        controller = new ProjectController();
        projectDAO = mock(ProjectDAOImpl.class);
        controller.setDao(projectDAO);

        when(projectDAO.fetch((long) 1)).thenReturn(dummyProject);
    }

    @Test
    public void shouldRenderTheProjectDetailsView() throws Exception {
        ModelAndView modelView = adapter.handle(request, response, controller);
        assertThat(modelView.getViewName(), is("project"));
    }

    @Test
    public void shouldFetchAProjectBasedOnTheIDProvided() throws Exception {
        adapter.handle(request, response, controller);
        verify(projectDAO, Mockito.times(1)).fetch((long) 1);
    }

    @Test
    public void shouldExposeTheProjectFetchedToTheProjectDetailsView() throws Exception {
        ModelAndView modelView = adapter.handle(request, response, controller);
        ModelMap modelMap = (ModelMap) modelView.getModel().get("model");
        assertThat((Project)modelMap.get("project"), is(dummyProject));
    }
}
