package org.taf.core.selenium_drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.taf.core.enums.HeadlessStrategyOperator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class IE implements SelDriverProvider {

    @Override
    public WebDriver getBrowser(boolean isHeadless) {
        WebDriverManager.iedriver().setup();
        if (isHeadless) {
            return HeadlessStrategyOperator.IE.execute ();
        } else {
            return new InternetExplorerDriver();
        }
    }
}
