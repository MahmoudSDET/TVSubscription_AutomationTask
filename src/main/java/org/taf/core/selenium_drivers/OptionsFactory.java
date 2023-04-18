package org.taf.core.selenium_drivers;

import org.jetbrains.annotations.NotNull;
import org.taf.core.custom_exceptions.UnSupportedOptionsException;
import org.taf.core.enums.Drivers;
import org.taf.utils.error_handlers.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class OptionsFactory {

    private static final String UN_SUPPORTED_BROWSER_OPTIONS = "Un-Supported browser Options: \" ";

    /**
     * @return Object of Browser options.
     */

    @NotNull
    public static MutableCapabilities getOptions(Drivers browser) {
        switch (browser) {
            case CHROMEREMOTE: {
                final DesiredCapabilities caps = new DesiredCapabilities ();

                caps.setBrowserName ("remote-chrome");
                return caps;

            }
            case CHROME: {
                ChromeOptions options = new ChromeOptions ();
                options.addArguments("--window-size=1400,600");
                options.addArguments("--no-sandbox");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--ignore-ssl-errors");
            //    options.addArguments("--disable-dev-shm-usage");
           //     options.addArguments("--disable-gpu");
                options.setCapability ("enable-features","NetworkServiceInProcess");
                options.setCapability ("enable-features","NetworkService");
                options.addArguments ("--enable-javascript");
                return options;




            }
            case FIREFOX: {
                return new FirefoxOptions();
            }
            case IE: {
                return new InternetExplorerOptions();
            }
            default: {
                Logger.info(UN_SUPPORTED_BROWSER_OPTIONS + browser + "\"");
                throw new UnSupportedOptionsException();
            }
        }
    }
}
