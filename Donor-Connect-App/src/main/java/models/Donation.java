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

    @Column
    private Date date;

    public Donation() {
    }

    public Donation(Project project, double amount, Date date) {
        this.project = project;
        this.amount = amount;
        this.date = date;
    }

    public Project getProject() {
        return project;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
