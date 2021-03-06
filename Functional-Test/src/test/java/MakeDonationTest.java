import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class MakeDonationTest extends InsertClass {

    String project_id;
    @Test
    public void checkHappyFlow(){
        project_id=paymentFlow();
        enterNumberAndDonate("1000");
        webDriver.findElement(By.id("btnCorrect")).click();
        waitForElementToLoad(webDriver,By.className("pageHeader"));
        assertThat(webDriver.getCurrentUrl().contains("http://www.donorsconnect.com:8080/Donor-Connect-App/project?id="+project_id+"&donationStatus=success"), is(true) );
    }

    @Test
    public void donationConfirmationAmount() {
        project_id=paymentFlow();
        enterNumberAndDonate("250");
        webDriver.findElement(By.id("btnCorrect")).click();
        waitForElementToLoad(webDriver,By.className("pageHeader"));
        assertThat(webDriver.findElement(By.xpath("//div[@id='successMessage']")).getText(), is("Thank you for your donation of\nRs. 250.0"));
    }

    @Test
    public void donationFailureMessagge() {
        project_id=paymentFlow();
        enterNumberAndDonate("250");
        webDriver.findElement(By.id("btnIncorrect")).click();
        waitForElementToLoad(webDriver,By.className("pageHeader"));
        assertThat(webDriver.findElement(By.xpath("//div[@id='errorMessage']")).getText(), is("Sorry!\n" +
                "We could not complete your donation.\n" +
                "Your account has not been charged.\n" +
                "Please try again."));
    }

    @Test
    public void dontSubmitThePayment(){
        project_id=paymentFlow();
        enterNumberAndDonate("1000");
        webDriver.findElement(By.id("btnIncorrect")).click();
        waitForElementToLoad(webDriver,By.className("pageHeader"));
        assertThat(webDriver.getCurrentUrl(), is("http://www.donorsconnect.com:8080/Donor-Connect-App/project?id="+project_id+"&donationStatus=failure"));
    }

    @Test
    public void checkEmailField(){
        checkForInvalidEmail("test.user1691@gmail");
        checkForInvalidEmail("test.user1691gmail.com");
        checkForInvalidEmail("  ");
        checkForInvalidEmail("test.user1691gmailcom");
    }

    @Test
    public void checkForBlankDonation(){
        checkForInvalidNumber(" ");
    }

    @Test
    public void checkForAlphabets(){
        checkForInvalidNumber("abcd");
        checkForInvalidNumber("123abc");
        checkForInvalidNumber("abc123");
        checkForInvalidNumber("abc 123");
    }

    @Test
    public void checkForSpecialCharacters(){
        checkForInvalidNumber("####");
        checkForInvalidNumber("#@#$$1234 ");
        checkForInvalidNumber("1234@#$$ ");
    }

    @Test
    public void checkForCombinations(){
        checkForInvalidNumber("#$hg@#$!bjhgj");
        checkForInvalidNumber("cvsdf!$@!%1234");
    }

    @Test
    public void checkForDonationRangeMoreThan220(){
        paymentFlow();
        webDriver.findElement(By.id("donationAmount")).sendKeys("219");
        webDriver.findElement(By.id("donorEmail")).sendKeys("test.user1691@gmail.com");
        webDriver.findElement(By.id("donateButton")).click();
        assertThat(webDriver.findElement(By.xpath("//span[contains(.,'Donation amount should be greater than 220.')]")).isDisplayed(),is(true));
    }

    @Test
    public void checkForDonationRangeLessThan570000(){
        paymentFlow();
        webDriver.findElement(By.id("donationAmount")).sendKeys("570001");
        webDriver.findElement(By.id("donorEmail")).sendKeys("test.user1691@gmail.com");
        webDriver.findElement(By.id("donateButton")).click();
        assertThat(webDriver.findElement(By.xpath("//span[contains(.,'Donation amount should be less than 570000.')]")).isDisplayed(),is(true));
    }


    public void checkForInvalidNumber(String data){
        paymentFlow();
        webDriver.findElement(By.id("donationAmount")).sendKeys(data);
        webDriver.findElement(By.id("donorEmail")).sendKeys("test.user1691@gmail.com");
        webDriver.findElement(By.id("donateButton")).click();
        assertThat(webDriver.findElement(By.xpath("//span[contains(.,'Please enter a valid number.')]")).isDisplayed(),is(true));

    }

    public void checkForInvalidEmail(String email){
        paymentFlow();
        webDriver.findElement(By.id("donationAmount")).sendKeys("1000");
        webDriver.findElement(By.id("donorEmail")).sendKeys(email);
        webDriver.findElement(By.id("donateButton")).click();
        assertThat(webDriver.findElement(By.xpath("//span[@class='ValidationErrors' and contains(.,'Please enter a valid email address.')]")).getText(),is("Please enter a valid email address."));

    }


}
