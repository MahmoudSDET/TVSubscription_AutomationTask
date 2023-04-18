package org.taf.page_objects;

import org.taf.utils.common.SharedMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.taf.page_objects.AbstractPageBases.PageBase.initiatePageDriver;

public class DashboardPage{


  // private WebDriver driver;
    public DashboardPage(){
        initiatePageDriver();
    }


   private By SearchIcon=By.cssSelector("li.inline-block.cursor-pointer.z-alert.relative.inline-block");

    private By FilterWithCountryBtn=By.xpath("//div[@class='head-links']//a[@id='country-btn']");

    private By PlanNames=By.xpath("//div[@class='plan-names']/div/strong");


    private By PlanPrices=By.xpath("//div[@class='plan-names']//div[@class='price']/b");


    private By PlanCurrencies=By.xpath("//div[@class='plan-names']//div[@class='price']/i");

    //div[@id='country']//span
    private By Country_drp=By.xpath("//div[@id='country']//span");


    public String getTheCountryFromDropDownList(){

       return SharedMethods.getElementText(Country_drp);
    }
    public FilterWithCountryPage goToTheCountryFilterPage(){

        SharedMethods.clickOn(FilterWithCountryBtn);
        return new FilterWithCountryPage();
    }


    public boolean checkPlanType(String plantype) {
             boolean existing_planType = false;
        for (WebElement el : SharedMethods.getTextOfElements(PlanNames)) {
            if (plantype.equals(el.getText()))
                existing_planType=true;
        }

    return existing_planType;

    }

    public List<Double> getPriceOfPlan(){
        List<Double>prices=new ArrayList<>();
       for(WebElement el:SharedMethods.getTextOfElements(PlanPrices)){
           prices.add(Double.parseDouble(el.getText()));


       }
        return prices;

    }
    public List<String> getCurrencyOfPlan(){
        List<String>currencies=new ArrayList<>();
        for(WebElement el:SharedMethods.getTextOfElements(PlanCurrencies)){
            currencies.add(el.getText().split("/")[0]);


        }
        return currencies;

    }




}
