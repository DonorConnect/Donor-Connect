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

    String project_id;
    @Test
    public void clickableSeeAll() {
        assertThat(webDriver.findElement(By.xpath("//div[@class='activeProject']/a[@href='projects']")).isDisplayed(),is(true));
        webDriver.findElement(By.xpath("//div[@class='activeProject']/a[@href='projects']")).click();
        waitForElementToLoad(webDriver,By.xpath("//div[@class='pageHeader']/h2"))  ;
        assertThat(webDriver.getCurrentUrl(), is("http://10.10.4.121:8080/Donor-Connect-App/projects"));
    }

    @Test
    public void checkLogoAndHeadline() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/projects");
        assertThat(webDriver.findElement(By.className("logopic")).isDisplayed(), is(true));
        assertThat(webDriver.findElement(By.xpath("//div[@class='pageHeader']/h2")).isDisplayed(), is(true));
    }

    @Test
    public void clickableThumbnail() {
        project_id = insertDataForCurrentProject("Project Check Clickable Thumbnail", "This is clickable thumbnail test", "image/children.jpg", "image/children_thumbnail.png", "This is project summary","2012-12-12","20000.00");
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/projects");
        WebElement thumbnail = webDriver.findElement(By.xpath(String.format("//div[@class='projectThumbnail']/a[@href='project?id=%s']", project_id)));

        assertThat(thumbnail.isDisplayed(), is(true));
        thumbnail.click();

        waitForElementToLoad(webDriver, By.xpath("//div[@class='pageHeader']/h2"));
        assertThat(webDriver.findElement(By.xpath("//div[@class='pageHeader']/h2")).getText(), is("Project Check Clickable Thumbnail"));
    }

    @Test
    public void clickableProjectName() {
        project_id = insertDataForCurrentProject("Project Check Clickable Thumbnail", "This is clickable thumbnail test", "image/children.jpg", "image/children_thumbnail.png", "This is project summary","2012-12-12","20000.00");
        System.out.println(project_id);
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/projects");
        webDriver.findElement(By.xpath("//h4/a[contains(text(), 'Project Check Clickable Thumbnail')]")).click();

        waitForElementToLoad(webDriver, By.xpath("//div[@class='pageHeader']/h2"));
        assertThat(webDriver.getCurrentUrl(), is("http://10.10.4.121:8080/Donor-Connect-App/project?id="+project_id));
    }

    @Test
    public void verifyCurrentProject() {
        project_id =insertDataForCurrentProject("Project_name", "Project_description", "image/children.jpg", "image/children_thumbnail.png", "Project_summary","2012-12-12","20000");
        verifyPositive("Project_name", "image/children_thumbnail.png", "Project_summary");
    }

    @Test
    public void verifyCompleteProject() {
        project_id =insertDataForCurrentProject("Project_name", "Project_description", "image/children.jpg", "image/children_thumbnail.png", "Project_summary","2012-12-12","20000");
        project_id =insertDataForCompleteProject("Children", "hjvbcv", "image/images.jpeg", "image/abc.jpeg", "Sailee wants dhang ka summary","2012-12-12","20000");
        verifyNegative("Children", "image/abc.jpeg", "Sailee wants dhang ka summary");
    }



    public void verifyPositive(String name, String thumbnail, String summary) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/projects");
        assertThat((webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div[@class='projectSummary']/div/h4/a[@class='projectName']")).getText()), is(name));
        assertThat((webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div[@class='projectSummary']/div/p[@class='projectBrief']")).getText()), is(summary));
        assertThat(webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div/a/img[@src='static/" + thumbnail + "']")).isDisplayed(),is(true));
    }

    public void verifyNegative(String name, String thumbnail, String summary) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/projects");
        assertThat(webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div[@class='projectSummary']/div/h4/a[@class='projectName']")).getText(),is(not(name)));
    }



}
