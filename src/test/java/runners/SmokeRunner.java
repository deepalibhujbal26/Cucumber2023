package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all feature files
        features ="src/test/resources/features/",
        //glue is where we find implementation of Gherkin steps
        //we provide the path of package where we defined  all the steps def
        glue = "steps",
        //if we set the value of dry run to true, it will stop the execution
        //if quickly scan all the gherkin steps in all the feature files and will give you the
        //missing step definition
        //**Actual execution we need to set it false
        dryRun = false,
        //tags will identify the scenarios in a group and will execute all the scenarios having these
        //tags="@ali or @nafiseh" or
        tags="@test123",
        //for gernerating the reports
        //pretty is responsible for printing the step definitions in console
        //in your framework all reports should be generated under target folder
        //we will generate HTML reports in taget folder
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json"}


)



public class SmokeRunner {
}
