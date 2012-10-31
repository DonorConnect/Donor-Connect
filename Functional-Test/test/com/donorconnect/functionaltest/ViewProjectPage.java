package com.donorconnect.functionaltest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ViewProjectPage {
    WebDriver wd = new HtmlUnitDriver();

    public void fillForm(String name, String description, String image) {
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/inject_project.ftl");
        wd.findElement(By.name("name")).sendKeys(name);
        wd.findElement(By.name("description")).sendKeys(description);
        wd.findElement(By.name("img")).sendKeys(image);

        wd.findElement(By.xpath("//div[@id='submit_button']/input")).click();

        WebElement element = (new WebDriverWait(wd, 4)).until(ExpectedConditions.visibilityOfElementLocated(By.id("project_id")));
        if(!element.isDisplayed())
        {
            System.exit(0);
        }
        String project_id = wd.findElement(By.id("project_id")).getText();
        wd.get("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl?project_id="+project_id);

        element = (new WebDriverWait(wd, 2)).until(ExpectedConditions.visibilityOfElementLocated(By.className("project-name")));
        if(!element.isDisplayed())
        {
            System.exit(0);
        }
    }


    /* @Test
    public void  TestScenario()
    {
          //Injection("gnkdjfg'\n'fdfgjdfgn","gdfg","ghfbhgb");
          //Injection("###############@@@@@@@@@%%%%%%%%%%bjhhhhhhhhhhhhhhhhhhhhhhhh bhjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjggggggggggggggggggggggggggggggggggggggggggggggggggggggggggbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjgfb cccvvvvvvvvvvvvvvvvvvvvvvvvvrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrmnmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmssssssssssssssssssssss;;;;;;;;;;;;;;;;;;;;;;","gdfg","ghfbhgb");
    } */

    public void assertPositive(String name, String description, String image){
        assertEquals(name, wd.findElement(By.className("project-name")).getText());
        assertEquals(wd.findElement(By.className("project-detail")).getText(), description);
        //System.out.println(wd.findElement(By.xpath("//img[@alt='children' and @src='"+image+"']")));
        assertTrue(wd.findElement(By.xpath("//img[@alt='children' and @src='" + image + "']")).isDisplayed());
        //Assert.assertTrue(wd.findElement(By.className("project-image")).isDisplayed());
    }


    public void assertNegative(String name, String description, String image){
        assertThat(wd.findElement(By.className("project-name")).getText(), not( is(name) ));
        assertEquals(wd.findElement(By.className("project-detail")).getText(), description);
        assertTrue(wd.findElement(By.xpath("//img[@alt='children' and @src='" + image + "']")).isDisplayed());
    }

    @Test
    public void verifyPageWithHappyPath(){
        fillForm("Sailee", "TWU29", "image/images.jpeg");
        assertPositive("Sailee", "TWU29", "image/images.jpeg");

    }

    @Test
    public void verifyPageWithSpecialCharAndNo(){
        fillForm("###############@@@@@@@@@%%%%%%%%%%", "gdfg", "ghfbhgb");
        assertPositive("###############@@@@@@@@@%%%%%%%%%%", "gdfg", "ghfbhgb");
        fillForm("456666666666666667", "7658777777777", "6788888888888888888888");
        assertPositive("456666666666666667", "7658777777777", "6788888888888888888888");
    }

    @Test
    public void verifyPageWithMultipleSpaces(){
        fillForm("C       r         w        q  a  r t", "gdfg", "ghfbhgb");
        assertNegative("C       r         w        q  a  r t", "gdfg", "ghfbhgb");
    }


    @Test
    public void verifyPageForLongProjectName() {
        fillForm("###############@@@@@@@@@%%%%%%%%%%bjhhhhhhhhhhhhhhhhhhhhhhhh bhjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjggggggggggggggggggggggggggggggggggggggggggggggggggggggggggbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", "gdfg", "ghfbhgb");
        assertPositive("###############@@@@@@@@@%%%%%%%%%%bjhhhhhhhhhhhhhhhhhhhhhhhh bhjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjggggggggggggggggggggggggggggggggggggggggggggggggggggggggggbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", "gdfg", "ghfbhgb");
    }
}
