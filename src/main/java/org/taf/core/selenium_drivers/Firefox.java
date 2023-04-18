package org.taf.core.selenium_drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.taf.core.enums.HeadlessStrategyOperator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Firefox implements SelDriverProvider {

    @Override
    public WebDriver getBrowser(boolean isHeadless) {
        WebDriverManager.firefoxdriver().setup();
        if (isHeadless) {
           return HeadlessStrategyOperator.FIREFOX.execute ();
        } else {
          return new FirefoxDriver();
        }
    }
}
