package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(generator = "projectId")
    @GenericGenerator(name = "projectId", strategy = "increment")
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String image;

    public Project(long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Project(String name, String description, String image) {
        this(0, name, description, image);
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }


    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Project() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }
}
