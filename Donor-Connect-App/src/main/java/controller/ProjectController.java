package controller;

import models.Donation;
import models.Project;
import models.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO dao;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String showProjectDetail(@ModelAttribute("model") ModelMap modelMap, @PathVariable int id){
        Project project = dao.fetch(id);

        modelMap.addAttribute("project", project);
        modelMap.addAttribute("anotherVar", id);
//        modelMap.addAttribute("donationPercentage", project.donationPercentage());


        return "project_detail";
    }

    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }
}
