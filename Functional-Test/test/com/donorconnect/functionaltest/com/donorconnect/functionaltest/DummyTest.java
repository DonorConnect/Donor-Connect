package com.donorconnect.functionaltest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DummyTest {
    @Test
    public void shouldLaunchApplicationInHtmlUnitDriver() throws Exception {
        WebDriver webDriver = new HtmlUnitDriver();

        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
        WebElement welcomeText = webDriver.findElement(By.xpath("//img[@src='image/children.jpg']"));
        welcomeText.isDisplayed();
//        assertEquals("welcome...", welcomeText);

    }
}
