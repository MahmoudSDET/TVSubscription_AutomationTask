package org.taf.core.enums;



public enum Drivers {
    /**
     * Chrome drivers.
     */
    CHROME("chrome"),

    /**
     * Safari drivers.
     */
    SAFARI("safari"),
    /**
     * Firefox drivers.
     */
    FIREFOX("firefox"),
    /**
     * Edge drivers.
     */
    EDGE("EDGE"),
    /**
     * Ie drivers.
     */
    IE("ie"),
    /**
     * Chrome Remote driver.
     */
    CHROMEREMOTE("CHROMEREMOTE"),
    /**
     * Android drivers.
     */
    @SuppressWarnings("unused")
    ANDROID("android"),
    /**
     * Ios drivers.
     */
    @SuppressWarnings("unused")
    IOS("ios");


    private final String value;

    Drivers(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
