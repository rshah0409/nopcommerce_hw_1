package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility {
    public WebDriver driver;


    /**
     * This method will click on element
     * @param by
     */
    public void clickOnElement(By by) {
    driver.findElement(by).click();
    }

    /**
     * This method will get Text from element.
     * @param by
     * @return
     */


    public String getTextFromElement(By by){

     String text = driver.findElement(by).getText();
     return text;

    }

    /**
     * This method will send Text to element.
     * @param by
     * @param text
     */
    public void sendTextToElement(By by,String text){
        driver.findElement(by).sendKeys(text);

    }

    /**
     * this method will select value from dropdown
     * @param by
     * @param value
     */
    public void selectByValueFromDropDown(By by,String value){
        Select select =new Select(driver.findElement(by)) ;
        select.selectByValue(value);

    }
    /**
     * This method will used to hover mouse on element
     */
    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).perform();
    }

}