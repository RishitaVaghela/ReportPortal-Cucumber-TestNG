package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class WebDriverWaitFactory {
    public static ExpectedCondition<Boolean> documentLoad;
    private static final Logger log = LoggerFactory.getLogger(WebDriverWaitFactory.class);
    static {
        documentLoad = driver -> {
            final JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean docReadyState = false;
            try {
                docReadyState = (Boolean) js.executeScript("return (function() { " +
                        "if (document.readyState != 'complete') {  return false; } " +
                        "if (window.jQuery != null && window.jQuery != undefined && window.jQuery.active) { return false;} " +
                        "if (window.jQuery != null && window.jQuery != undefined && window.jQuery.ajax != null && window.jQuery.ajax != undefined && window.jQuery.ajax.active) {return false;}  " +
                        "if (window.angular != null && angular.element(document).injector() != null && angular.element(document).injector().get('$http').pendingRequests.length) return false; " +
                        "return true;})();");
            } catch (WebDriverException e) {
                docReadyState = true;
            }
            return docReadyState;
        };
    }

    public static void waitForpageLoad(final WebDriver driver,int maxWait){
        FluentWait<WebDriver> wait = new WebDriverWait(driver, maxWait).pollingEvery(Duration.ofMillis(500)).ignoring(StaleElementReferenceException.class).withMessage("Page Load Timed Out");
        try {
            wait.until(documentLoad);
        } catch (TimeoutException e) {
        }
        log.info("Page loaded successfully!!");
    }

    public static boolean waitForElement(WebDriver driver, WebElement element, int maxWait) {
        boolean statusOfElementToBeReturned = false;
        WebDriverWait wait = new WebDriverWait(driver, maxWait);
        try {
            WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
            if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                statusOfElementToBeReturned = true;
                log.info("Element is displayed:: " + element.toString());
            }
        } catch (Exception ex) {
            statusOfElementToBeReturned = false;
            log.info("Unable to find a element ");
        }
        return statusOfElementToBeReturned;
    }

    public static boolean waitForListElement(WebDriver driver, List<WebElement> element, int maxWait) {
        boolean statusOfElementToBeReturned = false;
        WebDriverWait wait = new WebDriverWait(driver, maxWait);
        try {
            List<WebElement> waitElement = wait.until(ExpectedConditions.visibilityOfAllElements(element));
            if (waitElement.size()>0) {
                statusOfElementToBeReturned = true;
                log.info("Element is displayed:: " + element.toString());
            }
        } catch (Exception ex) {
            statusOfElementToBeReturned = false;
            log.info("Unable to find a element ");
        }
        return statusOfElementToBeReturned;
    }
}
