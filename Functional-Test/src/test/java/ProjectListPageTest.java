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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ProjectListPageTest {
    WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();

        clearProjects();

        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
    }

    @After
    public void tearDown() {
        webDriver.close();
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
        String projectId = insertDataAndCheck("Project Check Clickable Thumbnail", "This is clickable thumbnail test", "image/children.jpg", "image/children_thumbnail.png", "This is project summary", "Current");

        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        WebElement thumbnail = webDriver.findElement(By.xpath(String.format("//div[@class='projectThumbnail']/a[@href='project_detail.ftl?project_id=%s']", projectId)));
        assertThat(thumbnail.isDisplayed(), is(true));

        thumbnail.click();

        waitForElementToLoad(webDriver, By.xpath("//h2[@class='project-name']"));

        assertThat(webDriver.findElement(By.xpath("//h2[@class='project-name']")).getText(), is("Project Check Clickable Thumbnail"));
    }

    @Test
    public void clickableProjectName() {
        String projectId = insertDataAndCheck("Project Check Clickable Thumbnail", "This is clickable thumbnail test", "image/children.jpg", "image/children_thumbnail.png", "This is project summary", "Current");

        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        webDriver.findElement(By.xpath("//h4/a[contains(text(), 'Project Check Clickable Thumbnail')]")).click();

        waitForElementToLoad(webDriver, By.xpath("//h2[@class='project-name']"));

        assertThat(webDriver.getCurrentUrl(), is(String.format("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl?project_id=%s", projectId)));
    }

    public String insertDataAndCheck(String name, String description, String image, String thumbnail, String summary, String status) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/inject_project.ftl");
        webDriver.findElement(By.name("name")).sendKeys(name);
        webDriver.findElement(By.name("description")).sendKeys(description);
        webDriver.findElement(By.name("img")).sendKeys(image);
        if (status.equalsIgnoreCase("Complete")) {
            Select foo = new Select(webDriver.findElement(By.name("status")));
            foo.selectByValue("COMPLETED");
            // webDriver.findElement(By.name("status")).sendKeys(Keys.ARROW_DOWN);
        }
        webDriver.findElement(By.name("thumbnail")).sendKeys(thumbnail);
        webDriver.findElement(By.name("summary")).sendKeys(summary);
        webDriver.findElement(By.xpath("//div[@id='submit_button']/input")).click();

        WebElement element = (new WebDriverWait(webDriver, 4)).until(ExpectedConditions.visibilityOfElementLocated(By.id("project_id")));
        if (!element.isDisplayed()) {
            System.exit(0);
        }
        return webDriver.findElement(By.xpath("//span[@id='project_id']")).getText();
    }

    public void verifyPositive(String name, String thumbnail, String summary) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        Assert.assertEquals((webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div/h4/a[@class='projectName']")).getText()), name);
        Assert.assertEquals((webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div/p[@class='projectBrief']")).getText()), summary);
        assertTrue(webDriver.findElement(By.xpath("//div[@class='eachProject'][last()]/div/a/img[@src='" + thumbnail + "']")).isDisplayed());
    }


    public boolean isElementPresent(By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void verifyNegative(String name, String thumbnail, String summary) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        boolean x = isElementPresent(By.xpath("//div[@class='eachProject'][last()]/div/h4/a[@class='projectName']"));
        if (!x) {
            System.out.println("Test Successful");
        }
    }

    @Test
    public void VerifyCurrentProject() {
        insertDataAndCheck("Project_name", "Project_description", "image/children.jpg", "image/children_thumbnail.png", "Project_summary", "Current");
        verifyPositive("Project_name", "image/children_thumbnail.png", "Project_summary");
    }

    @Test
    public void VerifyCompleteProject() {
        insertDataAndCheck("Children", "hjvbcv", "image/images.jpeg", "image/abc.jpeg", "Sailee wants dhang ka summary", "Complete");
        verifyNegative("Children", "image/abc.jpeg", "Sailee wants dhang ka summary");
    }

    private void waitForElementToLoad(WebDriver webDriver, final By xpath) {
        WebDriverWait wait = new WebDriverWait(webDriver, 1000);
        wait.until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(@Nullable WebDriver input) {
                return input.findElement(xpath).isDisplayed();
            }
        });
    }

    private void clearProjects() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/inject_project.ftl");
        webDriver.findElement(By.xpath("//div[@id='delete_projects']/a")).click();
        waitForElementToLoad(webDriver, By.xpath("//div[@id='delete_projects']/a"));
    }
}
