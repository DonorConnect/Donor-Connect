import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePageTest extends InsertClass{
    private WebElement welcomeText;
    private WebElement welcomeImage;

    @Test
    public void homePageHasAllElements() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
        welcomeText = webDriver.findElement(By.xpath("//h1"));
        welcomeImage = webDriver.findElement(By.xpath("//img[@src='image/children.jpg']"));

        assertEquals("welcome...", welcomeText.getText());
        assertTrue(welcomeImage.isDisplayed());
    }
}

