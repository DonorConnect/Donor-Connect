package controller;

import models.Donation;
import models.Project;
import models.ProjectStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import java.util.Calendar;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.springframework.ui.ModelMap;

public class ConfirmationControllerTest {

    private MockHttpServletResponse response;
    private MockHttpServletRequest request;
    private AnnotationMethodHandlerAdapter adapter;
    private ConfirmationController controller;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        adapter = new AnnotationMethodHandlerAdapter();
        request.setRequestURI("/confirmation");
        request.setMethod("GET");
        request.setParameter("id", "1");
        request.setParameter("donationAmount", "3000.00");
        controller = new ConfirmationController();
    }

    @Test
    public void shouldReturnValuesForAPaymentRequest() throws Exception {
        ModelAndView modelView = adapter.handle(request, response, controller);
        ModelMap modelMap = (ModelMap) modelView.getModel().get("model");

        assertThat(modelView.getViewName(), is("confirmation"));
        assertThat((Integer) modelMap.get("projectID"), is(1));
        assertThat((Double)modelMap.get("donationAmount"), is(3000.00));
    }

    @Test
    public void shouldInsertDonationIntoProjectOnSuccess() throws Exception {
        Project dummyProject = new Project(1,"DummyProject", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 50000.00,
                ProjectStatus.CURRENT, "this is a dummy project", "description", "image/children.jpg", "image/children.jpg", 655);
        Donation dummyDonation = new Donation(dummyProject, 3000.00);
        long donationID = dummyDonation.getId();
        dummyProject.addDonation(dummyDonation);
        assertThat(dummyProject.getDonationAmount(donationID), is(donationID));
    }
}
