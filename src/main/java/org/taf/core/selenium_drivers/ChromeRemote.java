package org.taf.core.selenium_drivers;

import org.taf.core.enums.HeadlessStrategyOperator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.taf.utils.ExtentReport.DesiredListener.handleDesiredCaps;
import static org.taf.utils.PropReader.readConfig;


public class ChromeRemote implements SelDriverProvider {

    private static final String remoteUrl = readConfig("remoteurl");
    public static final String REMOTE_CHROME = "remote-chrome";


    /**
     * this method to create new remote webdriver instance.
     * headless to determine the headless implemention when run the remote driver
     *
     * @param isHeadless the is headless parameter is a boolean value true or false.
     * @return
     */
    @Override
    public WebDriver getBrowser(boolean isHeadless) {


        if (isHeadless) {
             return HeadlessStrategyOperator.CHROMEREMOTE.execute ();
        } else {
            try {

                DesiredCapabilities capabilities = DesiredCapabilities.chrome ();
                capabilities.setBrowserName (REMOTE_CHROME);
                handleDesiredCaps (capabilities);

                return new RemoteWebDriver (new URL (remoteUrl), capabilities);

            } catch (MalformedURLException e) {
                e.printStackTrace ();
            }
        }

         return null;
    }


}