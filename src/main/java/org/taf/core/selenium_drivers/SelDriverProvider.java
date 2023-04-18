package org.taf.core.selenium_drivers;


import org.taf.core.enums.HeadlessStrategyOperator;
import org.openqa.selenium.WebDriver;

/**
 * The interface Sel driver provider.
 */
public interface SelDriverProvider {

    /**
     * Gets browser.
     *
     * @param isHeadless the is headless
     * @return
     */
    WebDriver getBrowser(boolean isHeadless);

    /**
     * Handle headless browser.
     *
     * @param headlessStrategyOperator the headless strategy operator
     */
    default void handleHeadlessBrowser(HeadlessStrategyOperator headlessStrategyOperator) {
        HeadlessExecutor headlessExecutor = new HeadlessExecutor(headlessStrategyOperator);
        headlessExecutor.execute();
    }
}
