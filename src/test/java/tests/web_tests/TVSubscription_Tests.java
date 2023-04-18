package tests.web_tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.taf.data_models.TestData;
import org.taf.page_objects.DashboardPage;
import org.taf.page_objects.FilterWithCountryPage;
import org.testng.annotations.*;
import utils.CustomSoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static utils.DataReaders.getDataFromExcel;

public class TVSubscription_Tests extends TestBase{



    private  static CustomSoftAssert softAssert;


    @Test(description = "Check the TV subscription of Country", dataProvider= "getTestDataExcelSheet")
    public void checkTheClasses_SubscriptionTests(final TestData subscriptionDataModel) throws InterruptedException, IOException {

        // Arrange
        FilterWithCountryPage search_results =new FilterWithCountryPage();
        DashboardPage dashboardPage=new DashboardPage();


        test = extent.createTest("Check the subscription of "+subscriptionDataModel.getCountry()+ "["+subscriptionDataModel.getPlanType()+ "] Plan");

         if(!(dashboardPage.getTheCountryFromDropDownList().trim().equals(subscriptionDataModel.getCountry())))
         {
             search_results     = new DashboardPage().goToTheCountryFilterPage();
             dashboardPage= search_results.filterWithTheCountry(subscriptionDataModel.getCountry());

         }

        //assert
             softAssert=new CustomSoftAssert();
            test.log(Status.INFO,"Check the subscription plan type");
            System.out.println(subscriptionDataModel.getPlanType());

             failureMessage="we can not find the plan type"+" "+subscriptionDataModel.getPlanType();
            softAssert.assertTrue(dashboardPage.checkPlanType(subscriptionDataModel.getPlanType())," we can not find the plan type"+" "+failureMessage);

            test.log(Status.INFO,"Check the subscription price");
            failureMessage="we can not find the price"+" "+subscriptionDataModel.getPrice()+" in the "+ subscriptionDataModel.getPlanType();
        System.out.println(subscriptionDataModel.price);
        System.out.println(dashboardPage.getPriceOfPlan());
        System.out.println(dashboardPage.getPriceOfPlan().contains(subscriptionDataModel.price));
        softAssert.assertTrue(dashboardPage.getPriceOfPlan().contains(subscriptionDataModel.price),failureMessage);


        test.log(Status.INFO,"Check the currency");
        failureMessage="we cannot find the currency"+" "+subscriptionDataModel.getCurrency()+" in the "+ " "+subscriptionDataModel.getPlanType();
        System.out.println(subscriptionDataModel.currency);
        System.out.println(dashboardPage.getCurrencyOfPlan());
        System.out.println(dashboardPage.getCurrencyOfPlan().contains(subscriptionDataModel.getCurrency()));

        softAssert.assertEquals(dashboardPage.getCurrencyOfPlan().contains(subscriptionDataModel.getCurrency()),true,failureMessage);

       softAssert.assertAll();


    }


    @BeforeClass
    public void startReport(){


        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/Subscription_Report.html");
        htmlReporter.config().setReportName("TV Subscription TestCases Report");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterClass
    public  void flushReport(){

        extent.flush();
    }



    @DataProvider()
    private Iterator<Object[]> getTestDataExcelSheet() throws IOException {
        List<TestData> testData = getDataFromExcel(System.getProperty("user.dir") + "/src/test/resources/TV_Classes_Data.xlsx","Sheet1", TestData.class);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (TestData testData1 : testData) {
            dp.add(new Object[]{testData1});
        }
        return dp.iterator();

    }
}
