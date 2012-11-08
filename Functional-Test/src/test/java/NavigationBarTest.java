import org.junit.Test;
import org.openqa.selenium.By;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NavigationBarTest extends BaseClass{


    @Test
    public void checkLogoPicOnHomePage() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
        assertThat((webDriver.findElement(By.xpath("//div[@class='col6']/a/img[@class='logopic']")).isDisplayed()),is(true));
    }

    @Test
    public void checkLogoOnHomePage() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
        assertThat((webDriver.findElement(By.xpath("//div[@class='col6']/a/p[@class='logo']")).isDisplayed()),is(true));
    }

    @Test
    public void checkLogoOnHomePageRedirectsToHomePage() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
        webDriver.findElement(By.xpath("//div[@class='col6']/a/p[@class='logo']")).click();
        assertThat(webDriver.getCurrentUrl(),is("http://10.10.4.121:8080/Donor-Connect-App/home.ftl"));
    }

    @Test
    public void checkLogoPicOnHomePageRedirectsToHomePage() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
        webDriver.findElement(By.xpath("//div[@class='col6']/a/img[@class='logopic']")).click();
        assertThat(webDriver.getCurrentUrl(),is("http://10.10.4.121:8080/Donor-Connect-App/home.ftl"));
    }

    @Test
    public void homeLinkOnHomePageRedirectsToHomePage() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
        webDriver.findElement(By.xpath("//nav[@class='menubar']/ul/li/a[@href='home.ftl']")).click();
        assertThat(webDriver.getCurrentUrl(),is("http://10.10.4.121:8080/Donor-Connect-App/home.ftl"));
    }

    @Test
    public void projectsLinkOnHomePageRedirectsToAllProjectsPage() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/");
        webDriver.findElement(By.xpath("//nav[@class='menubar']/ul/li/a[@href='all_projects.ftl']")).click();
        assertThat(webDriver.getCurrentUrl(),is("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl"));
    }

    @Test
    public void homeLinkOnAllProjectPageRedirectsToHomePage() {
        webDriver.get("http://10.10.4.121:8080/Donor-Connect-App/all_projects.ftl");
        webDriver.findElement(By.xpath("//nav[@class='menubar']/ul/li/a[@href='home.ftl']")).click();
        assertThat(webDriver.getCurrentUrl(),is("http://10.10.4.121:8080/Donor-Connect-App/home.ftl"));
    }



}
