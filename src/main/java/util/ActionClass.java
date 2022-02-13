package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ActionClass {
    private static Logger log = LoggerFactory.getLogger(ActionClass.class);

    public static void clickOnElement(WebDriver driver, String pageName, String elementName) {
        WebElement element = PageObjectManager.getWebElement(driver,pageName,elementName);
        WebDriverWaitFactory.waitForElement(driver,element,10);
        scrollIntoView(driver,element);
        element.click();
        log.info("Clicked on element :: "+elementName +" on Page :: "+pageName);
    }

    public static void sendKeys(WebDriver driver, String pageName, String elementName, String data) {
        WebElement element = PageObjectManager.getWebElement(driver,pageName,elementName);
        WebDriverWaitFactory.waitForElement(driver,element,10);
        scrollIntoView(driver,element);
        element.sendKeys(data);
        log.info("Entered data for element :: "+elementName +" as :: "+data+" on Page :: "+pageName);
    }

    public static void selectOption(WebDriver driver, String pageName, String elementName, String option) {
        List<WebElement> element = PageObjectManager.getListWebElement(driver,pageName,elementName);
        if(element.size()==1) {
            selectOptionfromSelectTag(element.get(0),option);
        } else {
            WebDriverWaitFactory.waitForListElement(driver, element, 10);
            for (WebElement ele : element) {
                scrollIntoView(driver, ele);
                if (ele.getText().equals(option)) {
                    ele.click();
                    break;
                }
            }
        }
        log.info("Selected option for element :: "+elementName+" on Page :: "+pageName);
    }

    public static void selectOptionfromSelectTag(WebElement element, String option) {
        Select select  = new Select(element);
        select.selectByVisibleText(option);
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        } catch (Exception ex) {
            log.info("Moved to element..");
        }
    }

    public static void scrollIntoView(WebDriver driver, String pageName, String elementName) {
        WebElement element = PageObjectManager.getWebElement(driver,pageName,elementName);
        scrollIntoView(driver,element);
    }
}
