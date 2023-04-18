package org.taf.core.enums;

public class BrowserEnumParser {

    public static Drivers parse(String name) {

        switch (name.toUpperCase()) {
            case "CHROME":
                return Drivers.CHROME;
            case "FIREFOX":
                return Drivers.FIREFOX;
            case "EDGE":
                return Drivers.EDGE;
            case "IE":
                return Drivers.IE;
            case "CHROMEREMOTE":
                return Drivers.CHROMEREMOTE;
            case "SAFARI":
                return Drivers.SAFARI;
            default:
                throw new IllegalArgumentException("Browser Not Supported");
        }
    }
}

