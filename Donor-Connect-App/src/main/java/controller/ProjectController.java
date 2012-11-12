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
    public String showProjectDetail(@ModelAttribute("model") ModelMap modelMap, @RequestParam int id){
        Project project = dao.fetch(id);
        double donationAmount = dao.getDonationsAmount(project);
        modelMap.addAttribute("project", project);
        modelMap.addAttribute("donationPercentage", donationPercentage(project, donationAmount));
        modelMap.addAttribute("donationAmount", donationAmount);
        return "project";
    }

    private double donationPercentage(Project project, double donationAmount) {
        return donationAmount *100/project.getTargetAmount();
    }

    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }
}
