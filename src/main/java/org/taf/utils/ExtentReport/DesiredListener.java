package org.taf.utils.ExtentReport;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;


public class DesiredListener implements IInvokedMethodListener {

    private static ThreadLocal<String> desiredName = new ThreadLocal<>();


    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

        desiredName.set( iInvokedMethod.getTestMethod().getMethodName());

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    public static synchronized void handleDesiredCaps(DesiredCapabilities desiredCapabilities){
        desiredCapabilities.setCapability("name", desiredName.get());
    }
}
