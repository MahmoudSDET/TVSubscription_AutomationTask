package org.taf.page_objects.AbstractPageBases;

import org.openqa.selenium.WebDriver;

import static org.taf.core.instance.SelInstance.getDriver;

public  abstract class PageBase {
    private static WebDriver driver=null;

    public static WebDriver initiatePageDriver(){
      return  getDriverInstance() ;

    }

    private static synchronized WebDriver getDriverInstance(){
        if(driver==null){
         driver=getDriver();
        }
        return driver;

    }
}
