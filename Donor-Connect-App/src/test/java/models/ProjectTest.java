package models;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProjectTest {

    @Test
    public void shouldCalculateCurrentProjectAmount() throws Exception {
        Project project1 = new Project();
        Double currentAmount = project1.totalDonations();
        assertThat(currentAmount, is(0.0));
    }

    @Test
    public void shouldCalculateTotalDonationOf1000BasedOnDonationOf1000() throws Exception {
        Project project1 = new Project();
        Donation donation = new Donation(project1, 1000.0, Calendar.getInstance().getTime());
        project1.addDonation(donation);
        Double currentAmount = project1.totalDonations();
        assertThat(currentAmount, is(1000.0));
    }

    @Test
    public void shouldCalculateTotalDonationOf1500BasedOnDonationOf1000AndDonationOf500() throws Exception {
        Project project1 = new Project();
        Donation donation1 = new Donation(project1, 1000.0, Calendar.getInstance().getTime());
        Donation donation2 = new Donation(project1, 500.0, Calendar.getInstance().getTime());
        project1.addDonation(donation1);
        project1.addDonation(donation2);
        Double currentAmount = project1.totalDonations();
        assertThat(currentAmount, is(1500.0));
    }

}
