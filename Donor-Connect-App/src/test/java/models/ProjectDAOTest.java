package models;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.HSQLDialect;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

public class ProjectDAOTest {
    private ProjectDAO projectDAO = new ProjectDAO();

    @Before
    public void setUp() throws Exception {
        Configuration configuration =
                new Configuration();
        configuration.setProperty(
                Environment.DRIVER,
                "com.mysql.jdbc.Driver");
        configuration.setProperty(
                Environment.URL,
                "jdbc:hsqldb:mem:ProjectDAOTest");
        configuration.setProperty(
                Environment.USER, "root");
        configuration.setProperty(
                Environment.DIALECT,
                HSQLDialect.class.getName());
        configuration.setProperty(
                Environment.SHOW_SQL, "true");
        configuration.addClass(Project.class);

        SessionFactory sessionFactory =
                configuration.buildSessionFactory();
        HibernateTemplate hibernateTemplate =
                new HibernateTemplate(sessionFactory);
        projectDAO.setHibernateTemplate(hibernateTemplate);
    }

    @Test
    public void testStoreRetrieve() {
        Project project = new Project("My Project", "describtion");
        long id = projectDAO.store(project);

        Project retrievedProject = projectDAO.get(id);
        assertEquals(project, retrievedProject);
        assertNotSame(project, retrievedProject);
    }

}
