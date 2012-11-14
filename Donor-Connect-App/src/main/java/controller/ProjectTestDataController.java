package controller;

import models.Project;
import models.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProjectTestDataController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO dao;

    @RequestMapping(value = "/inject_project", method = RequestMethod.GET)
    public String renderInjectProject() {
        return "inject_project";
    }

    @RequestMapping(value = "/inject_project", method = RequestMethod.POST)
    public String injectProject(Project project, Model model) throws ParseException {
        dao.save(project);
        model.addAttribute("created_project_id", String.valueOf(project.getId()));

        return "inject_project";
    }

    @RequestMapping(value = "/delete_project", method = RequestMethod.GET)
    public String deleteAllProjects() {
        dao.deleteAll();
        return "inject_project";
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }
}
