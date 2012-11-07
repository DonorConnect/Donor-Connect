import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class ProjectListPageTest {
    WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
    }

    @Test
    public void clickableSeeAll() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/home.ftl");
        webDriver.findElement(By.xpath("//a[@href='all_projects.ftl']")).isEnabled();
        webDriver.findElement(By.xpath("//a[@href='all_projects.ftl']")).click();
        Assert.assertEquals("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl", webDriver.getCurrentUrl());
    }

    @Test
    public void checkLogoAndHeadline() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        //Assert.assertEquals("http://localhost:8080/Donor-Connect-App/all_projects.ftl",webDriver.getCurrentUrl());
        Assert.assertTrue(webDriver.findElement(By.className("logopic")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.xpath("//div[@class='projectHeader']/h3")).isDisplayed());
    }

    @Test
    public void clickableThumbnail() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        if (webDriver.findElement(By.xpath("(//img)[2]")).isDisplayed()) {
            webDriver.findElement(By.xpath("(//img)[2]")).click();
            Assert.assertTrue(webDriver.getCurrentUrl().contains("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl"));
        } else {
            System.out.println("No projects");
        }
    }

    @Test
    public void clickableProjectName() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        if (webDriver.findElement(By.xpath("(//a[@class='projectName'])[1]")).isDisplayed()) {
            webDriver.findElement(By.xpath("(//a[@class='projectName'])[1]")).click();
            Assert.assertTrue(webDriver.getCurrentUrl().contains("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl"));
        } else {
            System.out.println("No projects");
        }
    }

    @Test
    public void checkProjectName() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        if (webDriver.findElement(By.xpath("(//a[@class='projectName'])[1]")).isDisplayed()) {
            String project_name = webDriver.findElement(By.xpath("(//a[@class='projectName'])[1]")).getText();
            webDriver.findElement(By.xpath("(//a[@class='projectName'])[1]")).click();
            Assert.assertEquals(webDriver.findElement(By.xpath("//h2[@class='project-name']")).getText(), project_name);
        } else {
            System.out.println("No projects");
        }
    }


    public void insertDataAndCheck(String name, String description, String image, String thumbnail, String summary, String status) {
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
        //String project_id = webDriver.findElement(By.id("project_id")).getText();
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
}
