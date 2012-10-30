package models;

import java.util.List;

public interface ProjectDAO {
    Project save(Project project);

    Project fetch(long id);

    List<Project> fetchAll();

    void deleteAll();
}
