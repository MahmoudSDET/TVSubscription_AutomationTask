package org.taf.utils.ExtentReport;//package utils.ExtentReport;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.taf.core.instance.SelInstance.getDriver;
import static org.taf.utils.ExtentReport.ExtentManager.getReportFileLocation;

public class Utility {


    public static String getScreenShot(){
        Platform currentPlatform = PlatformFinder.getCurrentPlatform();
        String fileName = getReportFileLocation(currentPlatform);

        Date oDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String sDate = simpleDateFormat.format(oDate);
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;

                // todo check remote
        File source = ((RemoteWebDriver) getDriver()).getScreenshotAs (OutputType.FILE);
        String destination =fileName + sDate + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            throw new RuntimeException (e);
        }

        try {
            fileInputStream =new FileInputStream(finalDestination);
            byte[] bytes =new byte[(int)finalDestination.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
        return "data:image/png;base64,"+encodedBase64;

    }
}
