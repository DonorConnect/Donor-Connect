import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePageTest {
    WebDriver wd;
    WebElement welcomeText;
    WebElement welcomeImage;

    @Before
    public void BeforeTest() {
        wd = new HtmlUnitDriver();
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/");
    }

    @Test
    public void HomePageHasAllElements() {
        welcomeText = wd.findElement(By.xpath("//h1"));
        welcomeImage = wd.findElement(By.xpath("//img[@src='image/children.jpg']"));

        assertEquals("welcome...", welcomeText.getText());
        assertTrue(welcomeImage.isDisplayed());
    }
}

