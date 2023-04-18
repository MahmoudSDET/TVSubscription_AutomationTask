package org.taf.utils.ExtentReport;

import org.openqa.selenium.Platform;

public class PlatformFinder {
    private static final String NIX = "nix";
    private static final String OS_NAME = "os.name";
    private static final String WIN = "win";
    private static final String NUX = "nux";
    private static final String AIX = "aix";
    private static final String MAC = "mac";
    private static Platform platform = null;

    public static Platform getCurrentPlatform() {
        if (platform == null) {
            String operatingSystem = System.getProperty(OS_NAME).toLowerCase();
            if (operatingSystem.contains(WIN)) {
                platform = Platform.WINDOWS;
            } else if (operatingSystem.contains(NIX) || operatingSystem.contains(NUX)
                    || operatingSystem.contains(AIX)) {
                platform = Platform.LINUX;
            } else if (operatingSystem.contains(MAC)) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }
}
