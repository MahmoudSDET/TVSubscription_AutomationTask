package org.taf.utils.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.taf.core.instance.SelInstance;

import org.taf.utils.error_handlers.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public  class SharedMethods extends SelInstance {
    public static String winHandleBefore ;
   static JavascriptExecutor ex=(JavascriptExecutor)getDriver();


    public static String generateRandomString(){
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static String  GenerateRandomMobNumber(int count)
    {
        return (RandomStringUtils.randomNumeric(count));

    }

    public static void MouseOverAction(WebElement Locator)
    {
        String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";
        ((JavascriptExecutor)getDriver ()).executeScript(javaScript, Locator);
    }

    public static void MouseClickAction(WebElement Locator)
    {

        ex.executeScript("arguments[0].click()", Locator);
//        JavascriptExecutor jse = (JavascriptExecutor)getDriver ();
//        jse.executeScript("document.getElementByXpath('"+ Locator +"').click();");
    }

    public static void clearField(WebElement Locator){
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        while(!Locator.getAttribute("value").equals("")){
            Locator.sendKeys(Keys.BACK_SPACE);
        }
    }


    public static void clickAction(WebElement Locator)
    {
        Actions actions = new Actions(getDriver ());
        actions.moveToElement(Locator).click().build().perform();
    }


    //small hint here please don't forget to switch back to your main tap after calling this method and do your test :)
    public static void SwitchToNewTap(WebElement element)
    {
        JavascriptExecutor Js1 = (JavascriptExecutor) getDriver ();

        Js1.executeScript("window.scrollBy(0,4000)");
        element.click();

        winHandleBefore=getDriver ().getWindowHandle();
        for(String winHandle : getDriver ().getWindowHandles())
        {
            getDriver ().switchTo().window(winHandle);
        }
        getDriver ().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public static void SwitchToNewTapUsingSelenium(WebElement element){

        element.click();

        for(String winHandle : getDriver ().getWindowHandles())
        {
            getDriver ().switchTo().window(winHandle);
        }
        getDriver ().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

      public static int getCurrentDay() {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        //Get Current Day as a number

          return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static void waitUntilElementVisible(WebElement webElement)
    {
        WebDriverWait wait = new WebDriverWait(getDriver (),50);
        wait.until(ExpectedConditions.visibilityOf(webElement));

    }

    public static void waitUntilElementPresent(By Locator)
    {

        WebDriverWait wait = new WebDriverWait(getDriver (),50);
        wait.until(ExpectedConditions.presenceOfElementLocated(Locator));

    }
    public static void waitUntilElementClickable(WebElement webElement)
    {
        WebDriverWait wait = new WebDriverWait(getDriver (),30);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void mouseOverAction(WebElement Locator)
    {
        String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";
        ((JavascriptExecutor)getDriver ()).executeScript(javaScript, Locator);
    }

    public static void jsScrollUp(WebElement Locator)
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver ();
        //js.executeScript("window.scrollBy(0,-350)", Locator);
        js.executeScript("arguments[0].scrollIntoView(true);",Locator);
    }

    public static void jsScrollDown(WebElement Locator)
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver ();
        js.executeScript("arguments[0].scrollIntoView(true);", Locator);
    }

    public static void mouseClickAction(WebElement Locator)
    {




       // js.executeScript("arguments[0].scrollIntoView()", Locator);
        //js.executeScript("document.getElementByXpath('"+ Locator +"').click();");
        ex.executeScript("arguments[0].click()", Locator);

    }


    public static void waitTillClickAble (WebElement Locator)
    {
        WebDriverWait wait = new WebDriverWait(getDriver (),60);
        wait.until(ExpectedConditions.elementToBeClickable((Locator)));
    }

    public static void waitTillElementDisAppear (WebElement Locator)
    {
        WebDriverWait wait = new WebDriverWait(getDriver (),60);
        wait.until(ExpectedConditions.invisibilityOf ((Locator)));
    }

    public static void changeAttire(WebElement locator){
        JavascriptExecutor js = (JavascriptExecutor) getDriver ();
        js.executeScript("arguments[0].removeAttribute('disabled','disabled')",locator);
    }

    public static void pageBottom(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");

    }

    public static void threadSleep(int time){
        try {
            Thread.sleep (time);
        } catch (InterruptedException e) {
            throw new RuntimeException (e);
        }
    }

    public static boolean urlShouldInclude(String url){
        String URL = getDriver ().getCurrentUrl();
        return (URL.contains(url));
    }

    public static void clickOn(By Locator){
        try {
            waitUntilElementPresent(Locator);
            waitUntilElementVisible(getDriver().findElement(Locator));
            waitUntilElementClickable(getDriver().findElement(Locator));
            getDriver().findElement(Locator).click();
        }catch (Exception ex){
            Logger.info(getDriver().findElement(Locator)+ " is not Visible ");
           // Assert.fail(element + "is not Visible");


        }
    }

    public static void moveToElement(WebElement locator) {
        new Actions(getDriver()).moveToElement(locator).build().perform();
    }

    public static boolean elementContainsText(By locator, String text) {
        waitUntilElementVisible(getDriver().findElement(locator));

        String locatorText = getDriver().findElement(locator).getText().toLowerCase(Locale.ROOT);
        return locatorText.contains(text.toLowerCase(Locale.ROOT));
    }



    public static String getElementText(By locator) {
        waitUntilElementVisible(getDriver().findElement(locator));

        return getDriver().findElement(locator).getText();
    }
    public static String FieldValue (WebElement locator) {
        waitUntilElementVisible(locator);
        threadSleep(1000);
        String locatorText = locator.getText();
        return locatorText;
    }
    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    public static String findNextDay(int days)
    {
        Date nextDate = new Date(new Date().getTime() + MILLIS_IN_A_DAY * days);
        String timeStamp = new SimpleDateFormat("dd MMM yyyy").format(nextDate);
        return timeStamp;
    }
    public static void forceClickOnElement(WebElement locator) {
        Actions builder = new Actions(getDriver());
        builder.moveToElement( locator ).click( locator );
        builder.perform();

    }

    public static int getElementCount(By locator){
        List<WebElement> elements = getDriver().findElements(locator);
        return (int) elements.stream().count();


    }
    public static void setText (By locator,String text) {
        waitUntilElementVisible(getDriver().findElement(locator));
        getDriver().findElement(locator).sendKeys(text);
    }

    public static boolean checkElementExist (By locator) {
        boolean isExist=true;
        try {
            waitUntilElementVisible(getDriver().findElement(locator));
            isExist=  getDriver().findElement(locator).isDisplayed();
        }catch (Exception ex){
            isExist=false;
        }

        return isExist;
    }

    public static void pressEnter (By locator) {
        getDriver().findElement(locator).sendKeys(Keys.RETURN);
    }

    public static List<WebElement> getTextOfElements(By locator){

            waitUntilElementPresent(locator);
          return getDriver().findElements(locator);

    }

}
