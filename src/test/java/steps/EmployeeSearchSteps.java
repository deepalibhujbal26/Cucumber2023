package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.time.Duration;
import java.util.List;

public class EmployeeSearchSteps extends CommonMethods {

    // public WebDriver driver;
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
       // WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //  pimOption.click();
        click(dashboardPage.pimButton);
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @When("user clicks on Employee List Option")
    public void user_clicks_on_employee_list_option() {
      //  WebElement empListOption = driver.findElement(By.id("menu_pim_viewEmployeeList"));
        //empListOption.click();
        click(dashboardPage.empListButton);
        //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @When("user enters employee id")
    public void user_enters_employee_id() {
      //  WebElement employeeId = driver.findElement(By.id("empsearch_id"));
        // employeeId.sendKeys("25152165");
        sendText(employeeSearchPage.empSearchIdField, "25152165");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
     //   WebElement searchButton = driver.findElement(By.id("searchBtn"));
        //searchButton.click();
        click(employeeSearchPage.searchBtn);
        //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Then("user should be able to see employee details")
    public void user_should_be_able_to_see_employee_details() {


        //System.out.println("Test Passed for Employee Search with id");

        Assert.assertTrue("No result displayed",isResultDisplayed());
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
      //  WebElement nameTextField = driver.findElement(By.id("empsearch_employee_name_empName"));
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        sendText(employeeSearchPage.empSearchNameField, "mali");
        // nameTextField.sendKeys("mali");
    }

    private boolean isResultDisplayed(){


        int count=employeeSearchPage.resultRow.size();
        if(count==1){
            String rowText=employeeSearchPage.resultRow.get(0).getText();
            if(rowText.equals("No Records Found")){
                return false;

            }
            else {
                return true;
            }
        }
        else{
            return true;
        }

    }



}
