package models;

import java.util.List;

public interface ProjectDAO {
    Project save(Project project);

    Project fetch(long id);

    List<Project> fetchAllCurrent();

    void deleteAll();
    Project addDonationToProject(long id, Donation donation);

}
