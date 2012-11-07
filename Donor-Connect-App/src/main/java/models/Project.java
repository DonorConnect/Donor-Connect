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

    @Column(nullable = false)
    private String name;

    @Column (nullable = false ,columnDefinition = "LONGTEXT" ) 
    private String description;

    @Column(nullable = false)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private long charityId;
    private long defaultCharityId = 655;

    public Project(long id, String name, String description, String image, ProjectStatus status, String thumbnail, String summary, long charityId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
        this.thumbnail = thumbnail;
        this.summary = summary;
        this.charityId = charityId;
    }

    public Project(String name, String description, String image) {
        this(0, name, description, image, ProjectStatus.CURRENT ,"","", 655);
    }

    public Project(String name, String description, String image, ProjectStatus status, String thumbnail, String summary, long charityId) {
        this.name=name;
        this.description = description;
        this.image = image;
        this.status = status;
        this.thumbnail = thumbnail;
        this.summary = summary;
        this.charityId = charityId;
    }


    public Project() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSummary() {
        return summary;
    }

    public long getCharityId() {
        if (charityId == 0) {
            return defaultCharityId;
        }
        return charityId;
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

    public void setId(long id) {
        this.id = id;
    }
}
