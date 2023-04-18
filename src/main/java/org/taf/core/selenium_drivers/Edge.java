package org.taf.core.selenium_drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class Edge implements SelDriverProvider {

    @Override
    public WebDriver getBrowser(boolean isHeadless) {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
