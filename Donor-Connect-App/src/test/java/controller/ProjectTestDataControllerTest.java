package controller;

import models.Project;
import models.ProjectDAOImpl;
import models.ProjectStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.Calendar;
import java.util.Date;

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
        Date creationDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 10);
        Date endDate = calendar.getTime();
        dummyProject = new Project(100,"project_name", creationDate, endDate, 500.0, ProjectStatus.CURRENT, "summary", "project_desc", "img", "image/path", 655);

        request.setRequestURI("/inject_project.ftl");
        request.setMethod("POST");
        request.setParameter("name", "project_name");
        request.setParameter("description", "project_desc");
        request.setParameter("img", "img");
        request.setParameter("status","CURRENT");
        request.setParameter("thumbnail","thumbnail");
        request.setParameter("summary","summary");
        request.setParameter("charityId", "655");
        request.setParameter("endDate", "2012-11-21");
        request.setParameter("targetAmount", "200.0");

        testDataController = new ProjectTestDataController();
        projectDAO = mock(ProjectDAOImpl.class);
        testDataController.setDao(projectDAO);
    }

    @Test
    public void shouldInjectProject() throws Exception {
        ModelAndView modelAndView = adapter.handle(request, response, testDataController);

        verify(projectDAO).save(dummyProject);
        assertThat(modelAndView.getViewName(), is("inject_project"));
        String defaultValueOfId = String.valueOf(new Project().getId());
        assertThat((String) modelAndView.getModel().get("created_project_id"), is(defaultValueOfId));
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
