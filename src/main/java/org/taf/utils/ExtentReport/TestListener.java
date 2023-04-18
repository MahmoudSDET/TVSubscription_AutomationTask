package org.taf.utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.taf.core.instance.SelInstance;
import org.taf.utils.error_handlers.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static org.taf.utils.ExtentReport.Utility.getScreenShot;

public class TestListener extends SelInstance implements ITestListener {
    public static final String SKIPPED = "Skipped";
    public static final String TEST_SKIPPED = "Test Skipped";
    private static final String TEST_PASSED = "Test passed";
    private static final String STARTED = "started!";
    private static final String PASSED = "passed!";
    private static final String FAILED = "failed!";
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static volatile String nameTest;
    public static volatile String desiredName;



    @Override
    public synchronized void onStart(ITestContext context) {
        Logger.info("Extent Reports Test Suite " + STARTED);
    }


    @Override
    public synchronized void onFinish(ITestContext context) {
        Logger.info(("Extent Reports Test Suite is ending!"));
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        nameTest= getMethodName(result);
        Logger.info((nameTest + STARTED));
        ExtentTest extentTest = extent.createTest(getMethodName(result), result.getMethod().getMethodName());
        test.set(extentTest);
    }



    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        Logger.info((getMethodName(result) + PASSED));
        test.get().pass(TEST_PASSED);


    }

    private String getMethodName(ITestResult result) {
        return  result.getMethod().getDescription();
    }
    public synchronized void onTestFailure (ITestResult iTestResult) {
        System.out.println (getMethodName (iTestResult) + FAILED);
        test.get ().fail (iTestResult.getThrowable ().getMessage ());

        try {
            test.get ().addScreenCaptureFromPath (getScreenShot ());

        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logger.info((getMethodName(result) + SKIPPED));
        test.get().skip(TEST_SKIPPED);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Logger.info("onTestFailedButWithinSuccessPercentage for" + getMethodName(result));
    }







}