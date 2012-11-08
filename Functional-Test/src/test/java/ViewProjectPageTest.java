import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ViewProjectPageTest extends InsertClass {
    String project_id;

    @Before
    public void begin() {
        setUp();
    }

    @After
    public void end() {
        tearDown();
    }

    @Test
    public void verifyPageWithHappyPath(){
       project_id = insertDataForCurrentProject ("Sailee", "TWU29", "image/images.jpeg","image/children_thumbnail.png","Summary") ;
        assertPositive(project_id,"Sailee", "TWU29", "image/images.jpeg");

    }

    @Test
    public void verifyPageWithSpecialCharAndNo(){
        project_id =insertDataForCurrentProject("###############@@@@@@@@@%%%%%%%%%%", "gdfg", "ghfbhgb","image/children_thumbnail.png","Summary");
        assertPositive(project_id,"###############@@@@@@@@@%%%%%%%%%%", "gdfg", "ghfbhgb");
        project_id =insertDataForCurrentProject ("456666666666666667", "7658777777777", "6788888888888888888888","image/children.png","Summary") ;
        assertPositive(project_id,"456666666666666667", "7658777777777", "6788888888888888888888");
    }

    @Test
    public void verifyPageWithMultipleSpaces(){
        project_id =insertDataForCurrentProject ("C       r         w        q  a  r t", "gdfg", "ghfbhgb","image/children_thumbnail.png","Summary") ;
        assertNegative(project_id,"C       r         w        q  a  r t", "gdfg", "ghfbhgb");
    }


    @Test
    public void verifyPageForLongProjectName() {
        project_id =insertDataForCurrentProject("############### @@@@@@@@@ %%%%%%%%%% b j hhhhhhhhhhhhhhhhhhhhhhhh b h jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj gggggggggggggggggggggggggggggggggggggggggggggggggggggggggg bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", "gdfg", "ghfbhgb","image/children_thumbnail.png","Summary");
        assertPositive(project_id,"############### @@@@@@@@@ %%%%%%%%%% b j hhhhhhhhhhhhhhhhhhhhhhhh b h jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj gggggggggggggggggggggggggggggggggggggggggggggggggggggggggg bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", "gdfg", "ghfbhgb");
    }

    @Test
    public void VerifyLongDescription(){
        project_id =insertDataForCurrentProject ("Project_with_Long_description","aaaaaaaaaaaaaaaaa aaaaaaaaaaaaaas sssssssssssssss ssssssssssssdfdd dssssssssss sdddddddddddddddd dsssssssssss sdddddddddddddc sdddddddddd ssdccssdds dccdsddssddcdcdcds ddddddddddddd cccccccccccc dsssssssssssss sdddddddddddddddddd sdddddddddddddddddddddd dssssssssssssssssssss","image/children.jpeg","image/children_thumbnail.png","Summary");
        assertPositive(project_id,"Project_with_Long_description","aaaaaaaaaaaaaaaaa aaaaaaaaaaaaaas sssssssssssssss ssssssssssssdfdd dssssssssss sdddddddddddddddd dsssssssssss sdddddddddddddc sdddddddddd ssdccssdds dccdsddssddcdcdcds ddddddddddddd cccccccccccc dsssssssssssss sdddddddddddddddddd sdddddddddddddddddddddd dssssssssssssssssssss","image/children.jpeg");
    }

    public void assertPositive(String project_id,String name, String description, String image){
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl?project_id=" + project_id);
        waitForElementToLoad(webDriver,By.className("project-name")   )  ;
        assertEquals(name, webDriver.findElement(By.className("project-name")).getText());
        assertEquals(webDriver.findElement(By.className("project-detail")).getText(), description);
        assertTrue(webDriver.findElement(By.xpath("//img[@alt='children' and @src='" + image + "']")).isDisplayed());
    }


    public void assertNegative(String project_id,String name, String description, String image){
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/project_detail.ftl?project_id=" + project_id);
        waitForElementToLoad(webDriver,By.className("project-name")   )  ;
        assertThat(webDriver.findElement(By.className("project-name")).getText(), not( is(name) ));
        assertEquals(webDriver.findElement(By.className("project-detail")).getText(), description);
        assertTrue(webDriver.findElement(By.xpath("//img[@alt='children' and @src='" + image + "']")).isDisplayed());
    }

}