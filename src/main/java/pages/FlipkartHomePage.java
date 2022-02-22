package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import util.WebDriverWaitFactory;

import java.util.List;

public class FlipkartHomePage extends LoadableComponent<FlipkartHomePage> {
    WebDriver driver=null;
    boolean isPageLoaded = false;
    public FlipkartHomePage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,120);
        PageFactory.initElements(finder, this);
    }

    @Override
    protected void load() {
        WebDriverWaitFactory.waitForpageLoad(driver,120);

    }

    @Override
    protected void isLoaded() throws Error {
        isPageLoaded = true;
        if(WebDriverWaitFactory.waitForElement(driver,By.xpath("//button[@class='_2KpZ6l _2doB4z']"),30))
            driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
    }

    @FindBy(css=".eFQ30H .xtXmba")
    List<WebElement>menubar_titles;

    @FindBy(css = "._2WErco.row")
    WebElement footer;

    @FindBy(xpath = "//div[text()='POLICY']/following-sibling::a")
    List<WebElement> PolicyProducts;

    @FindBy(xpath = "//div[text()='ABOUT']/following-sibling::a")
    List<WebElement> AboutProducts;

    @FindBy(xpath = "//div[text()='HELP']/following-sibling::a")
    List<WebElement> HelpProducts;

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

    @FindBy(id="nav-cart1")
    WebElement cart;
}
