package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.SimpleTimeZone;

public class CommonMethods extends PageInitializer {
//Extend page Initializer
    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);


        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser name");
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        initializePageObjects();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));

    }

    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }

    public static void waitForClickablity(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickablity(element);
        element.click();
    }

    public static JavascriptExecutor getJsExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {
        getJsExecutor().executeScript("arguments[0].click()", element);

    }

    public void closeBrowser() {
        driver.quit(); //close all the tab windows
    }

    //take screenshot method for capturing all the screenshots
    public static byte[]takeScreenshot(String fileName){

        TakesScreenshot ts= (TakesScreenshot) driver;


        //in cucumber screenshot should be taken in array of byte format

        byte[]picByte=ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile=ts.getScreenshotAs(OutputType.FILE);

        //we will pass  the path of screenshot from constansts classs
        try {
            FileUtils.copyFile(sourceFile,
                    new File(Constants.SCREENSHOT_FILEPATH+fileName+" "+
                            getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  picByte;
    }

    //in java, we have module which returns current date and time
    public static String getTimeStamp(String pattern){
        Date date=new Date();
        //after getting the date, i need to format it as per my requirements

        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date); //it will return formatted date as per the pattern in string format

    }


}
