package controller;

import models.Donation;
import models.Project;
import models.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmationController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO projectDao;

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public ModelAndView persistDonationToProject(@ModelAttribute("model") ModelMap modelMap, @RequestParam int id,@RequestParam double donationAmount) {
        modelMap.addAttribute("projectId", id);
        Project project = projectDao.fetch(id);
        Donation donation = new Donation(project, donationAmount);
        projectDao.saveDonationToProject(donation);
        return new ModelAndView("redirect:project?id="+id+"&donationStatus=success&donationValue="+donationAmount);
    }

    @RequestMapping(value = "/failure", method = RequestMethod.GET)
    public ModelAndView onFailure(@ModelAttribute("model") ModelMap modelMap, @RequestParam int id) {
        modelMap.addAttribute("projectId", id);
        return new ModelAndView("redirect:project?id="+id+"&donationStatus=failure");
    }

    public void setDao(ProjectDAO projectDAO) {
        this.projectDao = projectDAO;
    }
}
