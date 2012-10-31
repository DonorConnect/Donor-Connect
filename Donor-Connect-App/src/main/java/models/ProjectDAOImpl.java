package models;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository("projectDAO")
@Transactional
public class ProjectDAOImpl implements ProjectDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public ProjectDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public Project save(Project project) {
        Project merge = entityManager.merge(project);
        entityManager.flush();
        project.setId(merge.getId());
        return project;
    }

    @Override
    public Project fetch(long id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> fetchAllCurrent() {
        return entityManager.createQuery("From Project").getResultList();
    }

    public void deleteAll() {
        Query deleteQuery = entityManager.createQuery("Delete From Project");
        deleteQuery.executeUpdate();
    }
}
