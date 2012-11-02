package controller;

import models.Project;
import models.ProjectDAO;
import models.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class InjectProjectController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO dao;

    @RequestMapping(value = "/inject_project.ftl", method = RequestMethod.GET)
    public String renderInjectProject() {
        return "inject_project";
    }

    @RequestMapping(value = "/inject_project.ftl", method = RequestMethod.POST)
    public ModelAndView injectProject(@RequestParam("name") String name, @RequestParam("description") String desc, @RequestParam("img") String img, @RequestParam("status") ProjectStatus status,
                                      @RequestParam("thumbnail") String thumbnail, @RequestParam("summary") String summary) {
        final Project project = dao.save(new Project(name, desc, img,status,thumbnail,summary));

        HashMap<String, String> model = new HashMap<String, String>() {{
            put("created_project_id", String.valueOf(project.getId()));
        }};
        return new ModelAndView("inject_project", model);
    }

    @RequestMapping(value = "/delete_project.ftl", method = RequestMethod.GET)
    public String deleteAllProjects() {
        dao.deleteAll();
        return "inject_project";
    }

    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }
}
