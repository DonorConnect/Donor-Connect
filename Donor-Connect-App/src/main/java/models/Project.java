package models;

import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.persistence.Entity;

@Entity
public class Project {

    private String name;
    private String desc;

    public Project(String name, String desc) {
        this.name=name;
        this.desc = desc;
    }


}
