package org.taf.page_objects;

import org.taf.utils.common.SharedMethods;
import org.openqa.selenium.By;

import static org.taf.page_objects.AbstractPageBases.PageBase.initiatePageDriver;

public class FilterWithCountryPage {

   // private WebDriver driver;


    public FilterWithCountryPage(){
        initiatePageDriver();
    }





    public DashboardPage filterWithTheCountry(String country){
        selectTheCountry(country);
        return new DashboardPage();

    }






    private void selectTheCountry(String country){

        SharedMethods.clickOn(By.xpath("//div[@id='country-selct']//span[contains(text(),'" + country + "')]"));
    }


}

