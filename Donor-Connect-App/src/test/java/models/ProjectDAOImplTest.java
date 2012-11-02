package models;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:hibernateContext-test.xml"})
@Transactional
public class ProjectDAOImplTest {
    @Autowired
    private ProjectDAO projectDAO;

    @Test
    public void shouldSaveAProject() {
        Project project = createProject();
        Project savedProject = projectDAO.save(project);

        Project fetchedProject = projectDAO.fetch(savedProject.getId());

        assertThat(fetchedProject, notNullValue());
    }

    @Test
    public void shouldFetchAllProjects() throws Exception {
        Project project1 = createProject();
        Project project2 = createProject();

        projectDAO.save(project1);
        projectDAO.save(project2);

        List<Project> projects = projectDAO.fetchAllCurrent();

        assertThat(projects.size(), is(2));
    }

    @Test
    public void shouldDeleteAllProjects() throws Exception {
        projectDAO.save(createProject());

        projectDAO.deleteAll();

        List<Project> projects = projectDAO.fetchAllCurrent();
        assertThat(projects.size(), is(0));
    }

    private Project createProject() {
        return new Project("test project", "test project description", "test/image");
    }
}
