package controller;

import models.Project;
import models.ProjectDAOTmp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

    private ProjectDAOTmp dao = ProjectDAOTmp.getInstance();

    @RequestMapping(value="/project_detail", method = RequestMethod.GET)
    public String showProjectDetail(@ModelAttribute("model") ModelMap modelMap, @RequestParam int project_id){

        Project project = dao.fetch(project_id);

        modelMap.addAttribute("project", project);

        return "project_detail";
    }

    public void setDao(ProjectDAOTmp dao) {
        this.dao = dao;
    }
}
