package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import util.WebDriverWaitFactory;

import java.util.List;

public class SearchPage extends LoadableComponent<SearchPage> {

    WebDriver driver=null;
    boolean isPageLoaded = false;
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    @Override
    protected void load() {
        WebDriverWaitFactory.waitForpageLoad(driver,120);
        if(!driver.findElement(By.id("search")).isDisplayed()) {
            Assert.fail("Page not loaded successfully!!");
        }
    }

    @Override
    protected void isLoaded() throws Error {
        isPageLoaded = true;
    }

    @FindBy(id="search")
    WebElement SearchPage;

    @FindBy(css = ".s-search-results.sg-row [data-component-type='s-search-result'] span.a-size-medium")
    List<WebElement> searchResult;



}
