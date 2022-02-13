package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ElementInteractionUtil {

    private static Logger log = LoggerFactory.getLogger(ElementInteractionUtil.class);

    public static void scrollIntoView(final WebDriver driver, WebElement element) {
        try {
            /*String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";*/
            ((JavascriptExecutor) driver).executeScript("argument[0].scrollIntoView(true)", element);
        } catch (Exception ex) {
            log.info("Moved to element..");
        }
    }
}
