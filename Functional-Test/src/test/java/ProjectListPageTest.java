import com.google.common.base.Predicate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.IsNot.not;

public class ProjectListPageTest extends InsertClass{
    WebDriver webDriver;

    @Before
    public void begin() {
        setUp();
    }

    @After
    public void end() {
        tearDown();
    }

    @Test
    public void clickableSeeAll() {
        webDriver.findElement(By.xpath("//a[@href='all_projects.ftl']")).isEnabled();
        webDriver.findElement(By.xpath("//a[@href='all_projects.ftl']")).click();
        assertThat(webDriver.getCurrentUrl(), is("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl"));
    }

    @Test
    public void checkLogoAndHeadline() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        assertThat(webDriver.findElement(By.className("logopic")).isDisplayed(), is(true));
        assertThat(webDriver.findElement(By.xpath("//div[@class='projectHeader']/h3")).isDisplayed(), is(true));
    }

    @Test
    public void clickableThumbnail() {
        String projectId = insertDataForCurrentProject("Project Check Clickable Thumbnail", "This is clickable thumbnail test", "image/children.jpg", "image/children_thumbnail.png", "This is project summary");

        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        WebElement thumbnail = webDriver.findElement(By.xpath(String.format("//div[@class='projectThumbnail']/a[@href='project_detail.ftl?project_id=%s']", projectId)));

        assertThat(thumbnail.isDisplayed(), is(true));
        thumbnail.click();

        waitForElementToLoad(webDriver, By.xpath("//h2[@class='project-name']"));
        assertThat(webDriver.findElement(By.xpath("//h2[@class='project-name']")).getText(), is("Project Check Clickable Thumbnail"));
    }

    @Test
    public void clickableProjectName() {
        String projectId = insertDataForCurrentProject("Project Check Clickable Thumbnail", "This is clickable thumbnail test", "image/children.jpg", "image/children_thumbnail.png", "This is project summary");

        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        webDriver.findElement(By.xpath("//h4/a[contains(text(), 'Project Check Clickable Thumbnail')]")).click();

        waitForElementToLoad(webDriver, By.xpath("//h2[@class='project-name']"));
        assertThat(webDriver.getCurrentUrl(), is(String.format("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl?project_id=%s", projectId)));
    }

    @Test
    public void verifyCurrentProject() {
        insertDataForCurrentProject("Project_name", "Project_description", "image/children.jpg", "image/children_thumbnail.png", "Project_summary");
        verifyPositive("Project_name", "image/children_thumbnail.png", "Project_summary");
    }

    @Test
    public void verifyCompleteProject() {
        insertDataForCurrentProject("Project_name", "Project_description", "image/children.jpg", "image/children_thumbnail.png", "Project_summary");
        insertDataForCompleteProject("Children", "hjvbcv", "image/images.jpeg", "image/abc.jpeg", "Sailee wants dhang ka summary");
        verifyNegative("Children", "image/abc.jpeg", "Sailee wants dhang ka summary");
    }



    public void verifyPositive(String name, String thumbnail, String summary) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        assertThat((webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div/h4/a[@class='projectName']")).getText()), is(name));
        Assert.assertEquals((webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div/p[@class='projectBrief']")).getText()), summary);
        assertTrue(webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div/a/img[@src='" + thumbnail + "']")).isDisplayed());
    }

    public void verifyNegative(String name, String thumbnail, String summary) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        assertThat(webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div/h4/a[@class='projectName']")).getText(),is(not(name)));
    }



}
