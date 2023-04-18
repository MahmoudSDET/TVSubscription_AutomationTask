
package org.taf.core.enums;

import org.taf.core.selenium_drivers.OptionsFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

import static org.taf.core.selenium_drivers.ChromeRemote.REMOTE_CHROME;
import static org.taf.utils.PropReader.readConfig;


/**
 * The enum Headless strategy operator.
 */
public enum HeadlessStrategyOperator {

    /**
     * The Chrome.
     */

    CHROME {
        @Override
        public WebDriver execute() {
            ChromeOptions options = (ChromeOptions) OptionsFactory.getOptions(Drivers.CHROME);
            options.setHeadless(true);
            options.addArguments("--window-size=1400,600");
            options.addArguments("--no-sandbox");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--ignore-ssl-errors");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.setCapability ("enable-features","NetworkServiceInProcess");
            options.setCapability ("enable-features","NetworkService");
            //options.addArguments("start-maximized");
            return new ChromeDriver(options);
        }
    },


    SAFARI{
        @Override
        public WebDriver execute() {
            SafariOptions options=(SafariOptions) OptionsFactory.getOptions(Drivers.SAFARI);
            return new SafariDriver(options);


        }
    },


    CHROMEREMOTE {
        public WebDriver execute() {


            final DesiredCapabilities caps = new DesiredCapabilities ();
                caps.acceptInsecureCerts ();
                caps.setJavascriptEnabled (true);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments ("window-size=1200x600");
                caps.setBrowserName (REMOTE_CHROME);
                caps.setCapability (ChromeOptions.CAPABILITY,chromeOptions);

                try {
                    return new RemoteWebDriver(new URL(readConfig(REMOTEURL)), caps);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }
    },
    /**
     * The Firefox.
     */
    FIREFOX {
        @Override
        public WebDriver execute() {
            FirefoxOptions options = (FirefoxOptions) OptionsFactory.getOptions(Drivers.FIREFOX);
            options.setHeadless(true);
            return new FirefoxDriver(options);
        }
    },
    /**
     * The Ie.
     */
    IE {
        @Override
        public WebDriver execute() {
            ChromeOptions options = (ChromeOptions) OptionsFactory.getOptions(Drivers.CHROME);
            options.setHeadless(true);
            InternetExplorerOptions ieOptions = (InternetExplorerOptions) OptionsFactory.getOptions(Drivers.IE);
            ieOptions.merge(options);
            return new InternetExplorerDriver(ieOptions);
        }

    };

    public static final String REMOTEURL = "remoteurl";

    /**
     * Execute.
     *
     * @return
     */
    public abstract WebDriver execute();
}
