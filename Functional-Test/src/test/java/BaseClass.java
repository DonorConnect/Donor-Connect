import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

public class BaseClass {

    WebDriver webDriver;

    public void setUp() {
        webDriver = new HtmlUnitDriver();
        clearProjects();
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
    }

    public void tearDown() {
        webDriver.close();
    }

    private void clearProjects() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/inject_project.ftl");
        webDriver.findElement(By.xpath("//div[@id='delete_projects']/a")).click();
        waitForElementToLoad(webDriver, By.xpath("//div[@id='delete_projects']/a"));
    }

    public void waitForElementToLoad(WebDriver webDriver, final By xpath) {
        WebDriverWait wait = new WebDriverWait(webDriver, 1000);
        wait.until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(@Nullable WebDriver input) {
                return input.findElement(xpath).isDisplayed();
            }
        });
    }
}
