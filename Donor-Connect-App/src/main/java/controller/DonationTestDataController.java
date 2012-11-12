package controller;

import models.Donation;
import models.Project;
import models.ProjectDAO;
import models.ProjectDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DonationTestDataController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO projectDao;

    @RequestMapping(value = "/inject_donation", method = RequestMethod.GET)
    public String renderInjectDonation() {
        return "inject_donation";
    }

    @RequestMapping(value = "/inject_donation", method = RequestMethod.POST)
    public ModelAndView InjectDonationData(@RequestParam("projectId") long projectId,
                                           @RequestParam("donationAmount") double amount){
        Project project = projectDao.fetch(projectId);
        Donation donation = new Donation(project,amount);
        projectDao.saveDonationToProject(donation);
        return new ModelAndView("inject_donation");
    }

    public void setDao(ProjectDAO projectDAO) {
        this.projectDao = projectDAO;
    }
}
