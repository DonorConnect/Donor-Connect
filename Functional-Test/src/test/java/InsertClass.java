import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class InsertClass extends BaseClass{
    public String insertDataForCurrentProject(String name, String description, String image, String thumbnail, String summary) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/inject_project.ftl");
        webDriver.findElement(By.name("name")).sendKeys(name);
        webDriver.findElement(By.name("description")).sendKeys(description);
        webDriver.findElement(By.name("img")).sendKeys(image);
        webDriver.findElement(By.name("thumbnail")).sendKeys(thumbnail);
        webDriver.findElement(By.name("summary")).sendKeys(summary);
        webDriver.findElement(By.xpath("//div[@id='submit_button']/input")).click();
        waitForElementToLoad(webDriver , By.id("project_id"));
        return webDriver.findElement(By.xpath("//span[@id='project_id']")).getText();
    }

    public String insertDataForCompleteProject(String name, String description, String image, String thumbnail, String summary) {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/inject_project.ftl");
        webDriver.findElement(By.name("name")).sendKeys(name);
        webDriver.findElement(By.name("description")).sendKeys(description);
        webDriver.findElement(By.name("img")).sendKeys(image);
        Select foo = new Select(webDriver.findElement(By.name("status")));
        foo.selectByValue("COMPLETED");
        webDriver.findElement(By.name("thumbnail")).sendKeys(thumbnail);
        webDriver.findElement(By.name("summary")).sendKeys(summary);
        webDriver.findElement(By.xpath("//div[@id='submit_button']/input")).click();
        waitForElementToLoad(webDriver , By.id("project_id"));
        return webDriver.findElement(By.xpath("//span[@id='project_id']")).getText();
    }


}
