package models;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ProjectTest {
    private Project project;

    @Before
    public void setUp() {
        project = new Project("My Project", "description", "image/path");
    }

    @Test
    public void testStoreRetrieve() {
        ProjectDAOTmp.getInstance().save(project);
        Project projectExpect = ProjectDAOTmp.getInstance().fetch(project.getId());
        assertEquals(project, projectExpect);
    }


}
