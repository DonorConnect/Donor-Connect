package models;

import java.util.List;

public interface ProjectDAO {
    void save(Project project);

    Project fetch(long id);

    List<Project> fetchAll();
}
