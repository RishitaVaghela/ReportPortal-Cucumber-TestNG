package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import util.WebDriverWaitFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePage extends LoadableComponent<HomePage> {
    WebDriver driver=null;
    boolean isPageLoaded = false;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        isPageLoaded = true;
    }

    @FindBy(id="glow-ingress-line2")
    WebElement deliver_location;

    @FindBy(id="nav-global-location-popover-link")
    WebElement select_deliver_location;

    @FindBy(css="#nav-xshop>a:not(.nav-hidden-aria)")
    List<WebElement>menubar_titles;

    @FindBy(css = ".a-popover-wrapper #a-popover-header-1")
    WebElement choose_your_Loction_popup;

    @FindBy(css = ".a-popover.a-dropdown .a-lgtbox-vertical-scroll .a-nostyle.a-list-link a")
    List<WebElement> location;

    @FindBy(id = "GLUXCountryValue")
    WebElement countryOption;

    @FindBy(xpath = "//button[text()='Done']")
    WebElement done;

    @FindBy(css = ".navFooterVerticalRow.navAccessibility")
    WebElement footer;

    @FindBy(xpath = "//div[text()='Amazon Payment Products']/following-sibling::ul//a")
    List<WebElement> AmazonPaymentProducts;

    @FindBy(id = "twotabsearchtextbox")
    WebElement search;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(css = "div.a-cardui-header>h2")
    List<WebElement> card_headers;

    @FindBy(css = ".s-suggestion-container")
    List<WebElement> suggestion_list;

    @FindBy(id="searchDropdownBox")
    List<WebElement> department;
}
