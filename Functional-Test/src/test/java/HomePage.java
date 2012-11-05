import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HomePage {
    WebDriver wd;
    WebElement logo;
    WebElement welcomeText;
    WebElement welcomeImage;

    @Before
    public void BeforeTest() {
        wd = new HtmlUnitDriver();
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/");
//     wd.manage().window().maximize();
    }

    @Test
    public void HomePageHasAllElements() {
        System.out.println(wd.getPageSource());
        logo = wd.findElement(By.xpath("(//img)[1]"));
        welcomeText = wd.findElement(By.xpath("//h1"));
        welcomeImage = wd.findElement(By.xpath("//img[@src='image/children.jpg']"));

        //logo.isDisplayed();
        Assert.assertEquals(welcomeText.getText(), "welcome...");
        welcomeImage.isDisplayed();
    }

    //@Test
    //public void HomePage

}

