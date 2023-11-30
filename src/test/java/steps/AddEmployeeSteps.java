package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.DbUtils;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    String empId;
    String firstNameFFE;
    String middleNameFFE;
    String lastNameFFE;

    String firstNameFBE;
    String middleNameFBE;
    String lastNameFBE;


    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        //  WebElement addEmployeeButton = driver.findElement(By.xpath("//*[@id='menu_pim_addEmployee']"));
        click(dashboardPage.addEmployeeButton);

    }

    @When("user enters firstName middleName and lastName")
    //We enter firstname ,middlename,lstname directly in steps
    public void user_enters_first_name_middle_name_and_last_name() {
        //   WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstName']"));
        //  WebElement middleNameField = driver.findElement(By.xpath("//input[@id='middleName']"));
        //  WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastName']"));
        sendText(addEmployeePage.firstNameLoc, "nind");
        sendText(addEmployeePage.middleNameLoc, "esha");
        sendText(addEmployeePage.lastNameLoc, "lata");
        empId = addEmployeePage.employeeIdLocator.getAttribute("value");


    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        // WebElement saveButton = driver.findElement(By.xpath("//input[@id='btnSave']"));
        click(addEmployeePage.saveBtn);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added");

    }

    @Then("user close the browser")
    public void user_close_the_browser() {
        closeBrowser();
    }

    @When("user enters {string} and {string} and {string}")
    //we pass the firstname,middlename,lastname in feature file of Add Employee
    public void user_enters_and_and(String firstName, String middleName, String lastName) {
        //  WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstName']"));
        //  WebElement middleNameField = driver.findElement(By.xpath("//input[@id='middleName']"));
        //  WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastName']"));

        sendText(addEmployeePage.firstNameLoc, firstName);
        sendText(addEmployeePage.middleNameLoc, middleName);
        sendText(addEmployeePage.lastNameLoc, lastName);

        firstNameFFE = firstName;
        middleNameFFE = middleName;
        lastNameFFE = lastName;
        empId = addEmployeePage.employeeIdLocator.getAttribute("value");


    }

    @When("user enters {string} and {string} and enters {string}")
    //we can pass the multiple data of FName,mName,lName using DDT , data is in addEmp feature file
    public void user_enters_and_and_enters(String firstName, String middleName, String lastName) {
        //  WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstName']"));
        //  WebElement middleNameField = driver.findElement(By.xpath("//input[@id='middleName']"));
        //  WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastName']"));

        sendText(addEmployeePage.firstNameLoc, firstName);
        sendText(addEmployeePage.middleNameLoc, middleName);
        sendText(addEmployeePage.lastNameLoc, lastName);
    }

    @When("user adds multiple employees from excel using {string} and verify them")
    public void user_adds_multiple_employees_from_excel_using_and_verify_them(String sheetName) throws InterruptedException {
        List<Map<String, String>> newEmployees = ExcelReader.read(sheetName, Constants.TESTDATA_FILEPATH);

        Iterator<Map<String, String>> itr = newEmployees.iterator();
        while (itr.hasNext()) {
            Map<String, String> employeeMap = itr.next();
            sendText(addEmployeePage.firstNameLoc, employeeMap.get("firstName"));
            sendText(addEmployeePage.middleNameLoc, employeeMap.get("middleName"));
            sendText(addEmployeePage.lastNameLoc, employeeMap.get("lastName"));
            //System.out.println(employeeMap.get("Photograph"));
            sendText(addEmployeePage.photograph, employeeMap.get("Photograph"));

            if (!addEmployeePage.checkBox.isSelected()) {
                click(addEmployeePage.checkBox);

            }
            sendText(addEmployeePage.usernameEmp, employeeMap.get("Username"));
            sendText(addEmployeePage.passwordEmp, employeeMap.get("Password"));
            sendText(addEmployeePage.confirmPassword, employeeMap.get("confirmPassword"));

            //we are storing the emp id from the locator
            String empIdValue =
                    addEmployeePage.employeeIdLocator.getAttribute("value");
            click(addEmployeePage.saveBtn);
            Thread.sleep(2000);

            //verification of employee still pending
            click(dashboardPage.empListButton);
            //we need to search the employee by the stored employee id
            sendText(employeeSearchPage.empSearchIdField, empIdValue);
            click(employeeSearchPage.searchBtn);

            //after searching the employee, it returns the info in format
            //empid firstname middlename lastname this is the format
            List<WebElement> rowData =
                    driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));

            for (int i = 0; i < rowData.size(); i++) {
                //it will give me the data from all the cell of the row
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                //it is we are getting from excel to compare with web table data
                String expectedDataFromExcel = empIdValue + " " + employeeMap.get("firstName")
                        + " " + employeeMap.get("middleName") + " "
                        + employeeMap.get("lastName");
                System.out.println(expectedDataFromExcel);
                Assert.assertEquals(expectedDataFromExcel, rowText);

            }
            //because we want to add many employees
            click(dashboardPage.addEmployeeButton);
            Thread.sleep(2000);

        }
    }

    @When("user adds multiple employees from data table")
    public void user_adds_multiple_employees_from_data_table(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {

        List<Map<String, String>> employeeNames = dataTable.asMaps();
        for (Map<String, String> map : employeeNames) {
            sendText(addEmployeePage.firstNameLoc, map.get("firstName"));
            sendText(addEmployeePage.middleNameLoc, map.get("middleName"));
            sendText(addEmployeePage.lastNameLoc, map.get("lastName"));
            click(addEmployeePage.saveBtn);
            Thread.sleep(2000);
            click(dashboardPage.addEmployeeButton);
            Thread.sleep(2000);
        }
    }

    @And("fetch employee info from backend")
    public void fetchEmployeeInfoFromBackend() {
        String query = " select * from hs_hr_employees where employee_id='" + empId + "';";
        List<Map<String, String>> data = DbUtils.fetch(query);
        Map<String, String> firstRow = data.get(0);
        firstNameFBE = firstRow.get("emp_firstname");
        middleNameFBE = firstRow.get("emp_middle_name");
        lastNameFBE = firstRow.get("emp_lastname");
    }

    @Then("verify employee info is properly stored in db")
    public void verifyEmployeeInfoIsProperlyStoredInDb() {

        Assert.assertEquals("Firstname from frondend is not same as backend", firstNameFFE, firstNameFBE);
        Assert.assertEquals("middlename from frondend is not same as backend", middleNameFFE, middleNameFBE);
        Assert.assertEquals("lastname from frondend is not same as backend", lastNameFFE, lastNameFBE);


    }
}
