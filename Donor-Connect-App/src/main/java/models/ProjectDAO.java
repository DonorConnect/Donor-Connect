package models;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class ProjectDAO {
    private HibernateTemplate hibernateTemplate;

    public long store(Project project) {
        Long key =
                (Long)hibernateTemplate.save(project);
        return key.longValue();
    }

    public Project get(long id) {
        return (Project)hibernateTemplate.get(
                Project.class, new Long(id));
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
