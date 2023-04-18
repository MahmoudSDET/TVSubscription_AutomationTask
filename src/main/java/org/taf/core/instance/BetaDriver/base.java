package org.taf.core.instance.BetaDriver;
import org.taf.core.instance.SelInstance;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class base extends SelInstance {

public static final String xmlPathName = "reengineering";


    public base ()
    {

    }

    @BeforeMethod
    public void navigateToLEcURL(){

    //get URL
    WebDriver driver = doBrowserSetup();

    threadLocalDriver.set(driver);
    threadLocalDriver.get ().get ("https://abd-stg.skidxb.com/en-ae/ski-dubai/");

}
    //get thread-safe driver

    @AfterMethod
    public void tearDown(){
        getDriver().quit();
        threadLocalDriver.remove();
    }

    }
