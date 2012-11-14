package controller;

import models.Project;
import models.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO dao;

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String showProjectDetail(@ModelAttribute("model") ModelMap modelMap, @RequestParam int id, @RequestParam(required=false, defaultValue = "default") String donationStatus, @RequestParam(required=false, defaultValue = "0") String donationValue){
        Project project = dao.fetch(id);
        modelMap.addAttribute("project", project);
        modelMap.addAttribute("donationPercentage", project.donationPercentage());
        modelMap.addAttribute("donationAmount", project.totalDonation());
        modelMap.addAttribute("anotherVar", id);
        modelMap.addAttribute("donationStatus", donationStatus);
        modelMap.addAttribute("donationValue", donationValue);
        return "project";
    }

    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }
}
