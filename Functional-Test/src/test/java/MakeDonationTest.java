import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class MakeDonationTest extends InsertClass {

    public void paymentFlow(){
        String project_id = insertDataForCurrentProject("Check Donation Page","Here I ll be checking the donation Page..","image/children.jpg","image/children_thumbnail.png","Still checking","2012-11-12","1000");

        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/project?id=" + project_id);
        waitForElementToLoad(webDriver,By.className("project-name")   )  ;

        webDriver.findElement(By.id("donationAmount")).sendKeys("1000");

        webDriver.findElement(By.id("donateButton")).click();
        waitForElementToLoad(webDriver,By.className("total")    );

        webDriver.findElement(By.id("DonorEmail")).sendKeys("test.user1691@gmail.com");

        webDriver.findElement(By.xpath("//ul[@class='paymentMethods clearfix']/li[@class='aPM']/label[@class='aPM_label aPM_Mastercard']")).click();
        waitForElementToLoad(webDriver,By.className("total"));
    }
    @Test
    public void checkHappyFlow(){
        paymentFlow();
        webDriver.findElement(By.id("btnCorrect")).click();
        waitForElementToLoad(webDriver,By.className("aS_infoMessage"));
        assertThat(webDriver.getTitle(), is("successful donation - ammado"));
    }

    @Test
    public void dontSubmitThePayment(){
        paymentFlow();
        webDriver.findElement(By.id("btnIncorrect")).click();
        waitForElementToLoad(webDriver,By.className("Donations-BibitDonationFailedApi"));
        assertThat(webDriver.getTitle(), is("donate - ammado"));


    }
}
