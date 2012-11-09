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
        return entityManager.createQuery("From Project where status = 'CURRENT'").getResultList();
    }


    public void deleteAll() {
        Query deleteQuery = entityManager.createQuery("Delete From Project");
        deleteQuery.executeUpdate();
    }

    @Override
    public Donation saveDonationToProject(Donation donation) {
        Donation donation1 = entityManager.merge(donation);
        entityManager.flush();
        return donation1;
    }
    @Override
    public double getDonationsAmount(Project project){
        List<Donation> donations = entityManager.createQuery("From Donation where project_id = :id ").setParameter("id",project.getId()).getResultList();
        Double totalAmount = 0.0;
        for (Donation donation : donations) {
            totalAmount += donation.getAmount();
        }
        return totalAmount;
    }
}
