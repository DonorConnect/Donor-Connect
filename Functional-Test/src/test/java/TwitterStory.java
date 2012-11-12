import org.junit.Test;
import org.openqa.selenium.By;

import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: saileebh
 * Date: 12/11/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class TwitterStory extends InsertClass {
    String project_id;

    @Test
    public void facebookShare() {
        project_id=insertDataForCurrentProject("TwitterStory","Checking the Twitter Story for sharing project on twitter","image/children.jpg","image/children.png","Testing Twitter Story","2012-03-04","20000");
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/project?id="+project_id);
        waitForElementToLoad(webDriver, By.xpath("//a[@title='Tweet This']"));

        webDriver.findElement(By.xpath("//a[@title='Tweet This']")).click();

        /*
        String parentWindowHandle = webDriver.getWindowHandle(); // save the current window handle.
        Set set = webDriver.getWindowHandles();
        Iterator<String> windowIterator = set.iterator();
        while(windowIterator.hasNext()) {
            String windowHandle = windowIterator.next();
            webDriver = webDriver.switchTo().window(windowHandle);
            if (webDriver.getTitle().equals("Log In | Facebook")) {

            }
        }
        waitForElementToLoad(webDriver, By.id("email"));
        webDriver.findElement(By.id("email")).sendKeys("pragyaj@thoughtworks.com");
        webDriver.findElement(By.id("pass")).sendKeys("test_user");
        webDriver.findElement(By.id("loginbutton")).click();
        set = webDriver.getWindowHandles();
        windowIterator = set.iterator();
        while(windowIterator.hasNext()) {
            String windowHandle = windowIterator.next();
            webDriver = webDriver.switchTo().window(windowHandle);
            if (webDriver.getTitle().equals("Facebook")) {

            }
        }
        waitForElementToLoad(webDriver,By.xpath("//div[@id='sharerDialogButtons']/label/input[@name='share']"));
        webDriver.findElement(By.xpath("//div[@id='sharerDialogButtons']/label/input[@name='share']")).click();
        webDriver = webDriver.switchTo().window(parentWindowHandle);
        parentWindowHandle = webDriver.getWindowHandle();
        System.out.println(parentWindowHandle);
        String URL = webDriver.getCurrentUrl();
        webDriver.get("http://www.facebook.com/donorconnect.thoughtworks");
        waitForElementToLoad(webDriver, By.xpath("(//div[@class='shareRedesign']/div)[1]"));
        webDriver.findElement(By.xpath("(//div[@class='shareRedesign']/div)[1]")).click();
        set = webDriver.getWindowHandles();
        windowIterator = set.iterator();
        while(windowIterator.hasNext()) {
            String windowHandle = windowIterator.next();
            webDriver = webDriver.switchTo().window(windowHandle);
            if (webDriver.getTitle().equals("Donors-Connect-Project")) {

            }
        }

                                                                               */
    }

}
