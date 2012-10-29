package controller;

import models.Project;
import models.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InjectProjectController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO dao;

    @RequestMapping(value="/inject_project.ftl", method = RequestMethod.POST)
    public String injectProject(@RequestParam("name") String name, @RequestParam("description") String desc, @RequestParam("img") String img){
        dao.save(new Project(name, desc, img));
        return "inject_project";
    }

    @RequestMapping(value="/inject_project.ftl", method = RequestMethod.GET)
    public String notInjectProject(){
        return "inject_project";
    }

    public void setDao(ProjectDAO dao) {
        this.dao = dao;
    }
}
