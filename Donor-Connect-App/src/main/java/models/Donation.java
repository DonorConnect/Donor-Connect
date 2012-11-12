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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Donation donation = (Donation) o;

        if (Double.compare(donation.amount, amount) != 0) return false;
        if (project != null ? !project.equals(donation.project) : donation.project != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = project != null ? project.hashCode() : 0;
        temp = amount != +0.0d ? Double.doubleToLongBits(amount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
