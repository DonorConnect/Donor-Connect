package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Donation")
public class Donation {

    @Id
    @GeneratedValue(generator = "donationId")
    @GenericGenerator(name = "donationId", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    @Column
    private double amount;

    public Donation() {
    }

    public Donation(Project project, double amount) {
        this.project = project;
        this.amount = amount;
    }

    public Project getProject() {
        return project;
    }

    public double getAmount() {
        return amount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
