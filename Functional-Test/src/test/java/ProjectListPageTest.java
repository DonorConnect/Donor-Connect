import junit.framework.Assert;
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
    WebDriver wd = new HtmlUnitDriver();

    @Test
    public void clickableSeeAll() {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/home.ftl");
        wd.findElement(By.xpath("//a[@href='all_projects.ftl']")).isEnabled();
        wd.findElement(By.xpath("//a[@href='all_projects.ftl']")).click();
        Assert.assertEquals("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl", wd.getCurrentUrl());
    }

    @Test
    public void checkLogoAndHeadline() {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        //Assert.assertEquals("http://localhost:8080/Donor-Connect-App/all_projects.ftl",wd.getCurrentUrl());
        Assert.assertTrue(wd.findElement(By.className("logopic")).isDisplayed());
        Assert.assertTrue(wd.findElement(By.xpath("//div[@class='projectHeader']/h3")).isDisplayed());
    }

    @Test
    public void clickableThumbnail() {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        if (wd.findElement(By.xpath("(//img)[2]")).isDisplayed()) {
            wd.findElement(By.xpath("(//img)[2]")).click();
            Assert.assertTrue(wd.getCurrentUrl().contains("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl"));
        } else {
            System.out.println("No projects");
        }
    }

    @Test
    public void clickableProjectName() {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        if (wd.findElement(By.xpath("(//a[@class='projectName'])[1]")).isDisplayed()) {
            wd.findElement(By.xpath("(//a[@class='projectName'])[1]")).click();
            Assert.assertTrue(wd.getCurrentUrl().contains("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl"));
        } else {
            System.out.println("No projects");
        }
    }

    @Test
    public void checkProjectName() {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        if (wd.findElement(By.xpath("(//a[@class='projectName'])[1]")).isDisplayed()) {
            String project_name = wd.findElement(By.xpath("(//a[@class='projectName'])[1]")).getText();
            wd.findElement(By.xpath("(//a[@class='projectName'])[1]")).click();
            Assert.assertEquals(wd.findElement(By.xpath("//h2[@class='project-name']")).getText(), project_name);
        } else {
            System.out.println("No projects");
        }
    }


    public void insertDataAndCheck(String name, String description, String image, String thumbnail, String summary, String status) {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/inject_project.ftl");
        wd.findElement(By.name("name")).sendKeys(name);
        wd.findElement(By.name("description")).sendKeys(description);
        wd.findElement(By.name("img")).sendKeys(image);
        if (status.equalsIgnoreCase("Complete")) {
            Select foo = new Select(wd.findElement(By.name("status")));
            foo.selectByValue("COMPLETED");
            // wd.findElement(By.name("status")).sendKeys(Keys.ARROW_DOWN);
        }
        wd.findElement(By.name("thumbnail")).sendKeys(thumbnail);
        wd.findElement(By.name("summary")).sendKeys(summary);
        wd.findElement(By.xpath("//div[@id='submit_button']/input")).click();

        WebElement element = (new WebDriverWait(wd, 4)).until(ExpectedConditions.visibilityOfElementLocated(By.id("project_id")));
        if (!element.isDisplayed()) {
            System.exit(0);
        }
        //String project_id = wd.findElement(By.id("project_id")).getText();
    }

    public void verifyPositive(String name, String thumbnail, String summary) {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        Assert.assertEquals((wd.findElement(By.xpath("//div[@class='eachProject'][last()]/div/h4/a[@class='projectName']")).getText()), name);
        Assert.assertEquals((wd.findElement(By.xpath("//div[@class='eachProject'][last()]/div/p[@class='projectBrief']")).getText()), summary);
        assertTrue(wd.findElement(By.xpath("//div[@class='eachProject'][last()]/div/a/img[@src='" + thumbnail + "']")).isDisplayed());
    }

    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void verifyNegative(String name, String thumbnail, String summary) {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
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
