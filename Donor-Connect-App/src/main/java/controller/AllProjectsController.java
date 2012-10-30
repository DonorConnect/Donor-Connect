package controller;


import models.Project;
import models.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AllProjectsController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO dao;

    @RequestMapping(value="/all_projects.ftl", method = RequestMethod.GET)
    public String showAllProjects(@ModelAttribute("model") ModelMap modelMap){
        List<Project> allProjects = dao.fetchAllCurrent();

        modelMap.addAttribute("allProjects", allProjects);

        return "all_projects";
    }

    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }
}
