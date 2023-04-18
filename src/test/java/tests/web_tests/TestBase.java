package tests.web_tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.taf.core.instance.SelInstance;
import org.taf.utils.PropReader;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class TestBase extends SelInstance {


    private final static String url = PropReader.readConfig("URL");
    private   static final String xmlPathName="reengineering";
    public  static String failureMessage=null;
    public TestBase(){//super(new ReengineeringXMLReader(xmlPathName));


         }

    protected ExtentHtmlReporter htmlReporter;
  protected   ExtentReports extent;
   public static ExtentTest test;

@BeforeClass
public void initDriver(){
    WebDriver driver=doBrowserSetup();

    threadLocalDriver.set(driver);
    threadLocalDriver.get().get(url);
}




    @AfterMethod()
    public void afterRunningMethod(ITestResult result) throws IOException {

       if(!result.isSuccess()){
         //  test.fail(failureMessage);
        //  test.addScreenCaptureFromPath(getScreenShot());
       }
    }
@AfterClass
public void tearDownDriver(){
    threadLocalDriver.get().quit();
    threadLocalDriver.remove();
}

}
