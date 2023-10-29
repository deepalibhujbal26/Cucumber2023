package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

import javax.security.auth.login.LoginContext;
import java.time.Duration;

public class LoginSteps extends CommonMethods {


    //public WebDriver driver;

    @Given("user is navigated to HRMS Application")
    public void user_is_navigated_to_hrms_application() {
        //driver=new ChromeDriver();
        // driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/dashboard");
        // Thread.sleep(2000);
        //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        openBrowserAndLaunchApplication();

    }

    @When("user enters admin username and password")
    public void user_enters_admin_username_and_password() {

        //object of login page to access the web elements
        //  LoginPage lp=new LoginPage();

        // WebElement usernameTextField=driver.findElement(By.xpath("//*[@id='txtUsername']"));
        // usernameTextField.sendKeys("admin");
        // Thread.sleep(2000);
        sendText(loginPage.usernameTextField, ConfigReader.getPropertyValue("username"));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //WebElement passwordTextField= driver.findElement(By.cssSelector("input#txtPassword"));
        // passwordTextField.sendKeys("Hum@nhrm123");

        sendText(loginPage.passwordTextField, ConfigReader.getPropertyValue("password"));


    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //  LoginPage lp=new LoginPage();
        //WebElement loginButton= driver.findElement(By.xpath("//*[@value='LOGIN']"));
        click(loginPage.loginBtn);
        // loginButton.click();

    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        System.out.println("My  Log in test Passed ");

        //aseertion to check if the element exixts
        //moment assertion finds the error it will stops the execution
        //gerneally we used assertTrue , check boolean condtion return true =test path and return false=failed
       // boolean isDisplayed=dashboardPage.welcomeAdminLocator.isDisplayed();
        boolean isDisplayed= dashboardPage.isElementDisplayed(dashboardPage.welcomeAdminLocator);
        Assert.assertTrue("Element is not displayed", isDisplayed);
        //System.out.println(1/0);
       // Assert.assertEquals();



    }


}
