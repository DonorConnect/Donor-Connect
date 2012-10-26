package models;

public interface ProjectDAO {
    void save(Project project);

    Project fetch(long id);

}
