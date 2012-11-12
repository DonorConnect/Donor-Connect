package controller;

import models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class DonationTestDataControllerTest {

    private MockHttpServletResponse response;
    private MockHttpServletRequest request;
    private AnnotationMethodHandlerAdapter adapter;
    private DonationTestDataController testDataController;
    private Project dummyProject;
    private Donation dummyDonation;
    private ProjectDAO projectDAO;


    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        adapter = new AnnotationMethodHandlerAdapter();
        Date creationDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 10);
        Date endDate = calendar.getTime();
        int project_id = 100;
        dummyProject = new Project(project_id, "project_name", creationDate, endDate, 500.0, ProjectStatus.CURRENT, "summary", "project_desc", "img", "image/path", 655);
        dummyDonation = new Donation(dummyProject, project_id);

        request.setRequestURI("/inject_donation");
        request.setMethod("POST");
        request.setParameter("donationAmount", "100");
        request.setParameter("projectId", String.valueOf(project_id));

        testDataController = new DonationTestDataController();

        projectDAO = mock(ProjectDAOImpl.class);
        testDataController.setDao(projectDAO);
    }

    @Test
    public void shouldInsertDonation() throws Exception {
        when(projectDAO.fetch(100)).thenReturn(dummyProject);

        ModelAndView modelAndView = adapter.handle(request, response, testDataController);

        verify(projectDAO).saveDonationToProject(dummyDonation);
        assertThat(modelAndView.getViewName(), is("inject_donation"));
    }

    @Test
    public void shouldNotInjectDonationForGetMethod() throws Exception {
        request.setMethod("GET");
        adapter.handle(request, response, testDataController);
        verify(projectDAO, Mockito.times(0)).fetch(100);
    }
}
