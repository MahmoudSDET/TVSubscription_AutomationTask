package org.taf.utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.taf.utils.error_handlers.Logger;
import org.openqa.selenium.Platform;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static final String UTF_8 = "utf-8";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String HTML_EXTENSION = ".html";
    private static ExtentReports extent;

    // todo have the report name configured for each CC based on the run
    private static String reportFileName = "Subscription STC TV -Automation Report";
    private static String macPath = System.getProperty("user.dir") +"/target/"+ "/TestReport";
    private static String windowsPath = System.getProperty("user.dir") + "/target/"+"\\TestReport";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;
    private static String linuxPath = System.getProperty("user.dir") +"/target/"+ "/TestReport";
    private static String linuxReportFileLoc = linuxPath + "/" + reportFileName;


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {
        Platform currentPlatform = PlatformFinder.getCurrentPlatform();
        String fileName = getReportFileLocation(currentPlatform);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding(UTF_8);
        htmlReporter.config().setReportName("Subscription STC TV -Automation Report");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

    static String getReportFileLocation (Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc + "--" + getTime();
                createReportPath(macPath);
                Logger.info("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc + "--" + getTime();
                createReportPath(windowsPath);
                Logger.info("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            case LINUX:
                reportFileLocation = linuxReportFileLoc + "--" + getTime();
                createReportPath(linuxPath);
                Logger.info("ExtentReport Path for linux: " + linuxPath + "\n");
                break;

            default:
                Logger.info("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }

    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                Logger.info("Directory: " + path + " is created!");
            } else {
                Logger.info("Failed to create directory: " + path);
            }
        } else {
            Logger.info("Directory already exists: " + path);
        }
    }

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(date) + HTML_EXTENSION;

    }

}
