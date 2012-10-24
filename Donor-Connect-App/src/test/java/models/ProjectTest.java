package models;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ProjectTest {
    private Project project = new Project("My Project", "description", "image/path");

    @Test
    public void testStoreRetrieve() {
        ProjectDAO.getInstance().save(project);

    }
    @Test
    public void testFetchRetrieve() {
        Project project_expect = ProjectDAO.getInstance().fetch((long) 1);
        assertEquals(project, project_expect);

    }

}
