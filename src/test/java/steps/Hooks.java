package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks  extends CommonMethods {
    @Before
    public void start(){
        openBrowserAndLaunchApplication();

    }
    @After
    //this executes always at the end irrespective of the result
    public void end(Scenario scenario){
        byte[]pic;
        //here we can take the screenshot before closing the browser
        //scenrio class in cucumber which will give me information of the execution ,
        //it holds the complete info of the execution

        //getname method will return the name of the scenario
        //if the scenrio fail add picture in failed folder under screenshot
       if(scenario.isFailed()) {

           pic = takeScreenshot("failed/"+scenario.getName());
       }else{
           pic = takeScreenshot("passed/"+scenario.getName());

       }
       //pic of array of byte , imge/png is media type ,getname is name of scenrio
       scenario.attach(pic,"image/png",scenario.getName());

        closeBrowser();


    }

}
