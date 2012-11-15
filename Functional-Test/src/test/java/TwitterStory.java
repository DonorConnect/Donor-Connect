import org.junit.Test;
import org.openqa.selenium.By;

import java.util.Iterator;
import java.util.Set;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class TwitterStory extends InsertClass {
    String project_id;

    @Test
    public void TwitterShare() {
        project_id=insertDataForCurrentProject("TwitterStory","Checking the Twitter Story for sharing project on twitter","image/children.jpg","image/children.png","Testing Twitter Story","2012-12-12","20000");
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/project?id="+project_id);
        waitLongForElementToLoad(webDriver, By.xpath("//a[@title='Tweet This']"));

        webDriver.findElement(By.xpath("//a[@title='Tweet This']")).click();
        String parentWindowHandle = webDriver.getWindowHandle(); // save the current window handle.
        Set set = webDriver.getWindowHandles();
        Iterator<String> windowIterator = set.iterator();
        while(windowIterator.hasNext()) {
            String windowHandle = windowIterator.next();
            webDriver = webDriver.switchTo().window(windowHandle);
        }

        waitLongForElementToLoad(webDriver, By.id("username_or_email"));
        assertThat(webDriver.getCurrentUrl().contains("twitter.com"), is(true));

    }
}
