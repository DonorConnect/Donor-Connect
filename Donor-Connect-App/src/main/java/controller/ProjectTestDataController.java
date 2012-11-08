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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@Controller
public class ProjectTestDataController {
    @Autowired
    @Qualifier("projectDAO")
    private ProjectDAO dao;

    @RequestMapping(value = "/inject_project.ftl", method = RequestMethod.GET)
    public String renderInjectProject() {
        return "inject_project";
    }

    @RequestMapping(value = "/inject_project.ftl", method = RequestMethod.POST)
    public ModelAndView injectProject(@RequestParam("name") String name,
                                      @RequestParam("description") String desc,
                                      @RequestParam("img") String img,
                                      @RequestParam("status") ProjectStatus status,
                                      @RequestParam("thumbnail") String thumbnail,
                                      @RequestParam("summary") String summary,
                                      @RequestParam("charityId") String charityId,
                                      @RequestParam("endDate") String endDate,
                                      @RequestParam("targetAmount") Double targetAmount) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(endDate);
        final Project project = dao.save(new Project(name, desc, img,status,thumbnail,summary, Long.valueOf(charityId), date, targetAmount));

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
