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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AllProjectsControllerTest {

    private MockHttpServletResponse response;
    private MockHttpServletRequest request;
    private AnnotationMethodHandlerAdapter adapter;
    private AllProjectsController controller;
    private Project dummyProject1,dummyProject2;
    private models.ProjectDAO projectDAO;
    private List<Project> allProjects;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        adapter = new AnnotationMethodHandlerAdapter();
        dummyProject1 = new Project(0, "name", "description", "image/path", ProjectStatus.CURRENT,"image/path","summary");
        dummyProject2 = new Project(0, "name2", "description2", "image/path2",ProjectStatus.CURRENT,"image/path","summary");

        request.setRequestURI("/all_projects.ftl");
        request.setMethod("GET");

        controller = new AllProjectsController();
        projectDAO = mock(ProjectDAOImpl.class);
        controller.setDao(projectDAO);

        allProjects = new ArrayList<Project>();
        allProjects.add(dummyProject1);
        allProjects.add(dummyProject2);
        when(projectDAO.fetchAllCurrent()).thenReturn(allProjects);
    }

    @Test
    public void shouldRenderAllProjectsView() throws Exception {
        ModelAndView modelView = adapter.handle(request, response, controller);
        assertThat(modelView.getViewName(), is("all_projects"));
    }

    @Test
    public void shouldFetchAllProjects() throws Exception {
        adapter.handle(request, response, controller);
        verify(projectDAO, Mockito.times(1)).fetchAllCurrent();
    }

    @Test
    public void shouldExposeTheProjectsFetchedToAllProjectsView() throws Exception {
        ModelAndView modelView = adapter.handle(request, response, controller);
        ModelMap modelMap = (ModelMap) modelView.getModel().get("model");

        List<Project> allProjectsFromModel = (List<Project>) modelMap.get("allProjects");

        assertThat(allProjects.size(), is(allProjectsFromModel.size()));
        assertThat(allProjects, is(allProjectsFromModel));
    }

}
