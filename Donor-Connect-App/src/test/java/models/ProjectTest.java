package models;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProjectTest {


    @Test
    public void shouldCalculateDaysLeft() {
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2012, 11, 1);
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.set(2012, 12, 10);
        Project project = new Project(0,"DummyProject", creationDate.getTime(), endDateCalendar.getTime(), 0.0,
                ProjectStatus.CURRENT, "this is a dummy project", "description", "image/children.jpg", "image/children.jpg", (long)1 );
        assertThat(project.leftDays(), is(40));
    }


}
