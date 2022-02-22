package stepdefinition;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class stepdefs {
    WebDriver driver;
    PropertyReader reader =  PropertyReader.getInstance();
    GifAssembler assembler = new GifAssembler();

    @Given("^user navigates to \"([^\"]*)\"$")
    public void user_navigates_to(String url){
        driver = WebDriverFactory.get(reader.getProperty("browserName"));
        driver.get(url);
        WebDriverWaitFactory.waitForpageLoad(driver,120);
    }

    @Then("validate title as {string}")
    public void validateTitleOn(String value) {
        String title = driver.getTitle();
        Assert.assertEquals(title,value);
    }

    @And("validate {string} has text {string} on {string}")
    public void validateHasTextOn(String elementName, String expectedValue, String pageName) {
        String actualText = PageObjectManager.getTextWebElement(driver,pageName,elementName);
        Assert.assertTrue(actualText.equals(expectedValue),"Expected value : "+expectedValue+" but found : "+actualText);
    }

    @And("validate list of {string} has value as {string} on {string}")
    public void validateOn(String elementName,String data, String pageName) {
        List<String> actualdata = PageObjectManager.getTextListWebElement(driver,pageName,elementName);
        List<String> expectedData = Arrays.asList(data.split(","));
        Assert.assertTrue(actualdata.containsAll(expectedData),"Expected value : "+expectedData+" but found : "+actualdata);
    }

    @After
    public void after(Scenario scenario) throws InterruptedException, IOException {
        // if(scenario.isFailed()) {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        byte img[]=screenshot.getScreenshotAs(OutputType.BYTES);
        byte[] animation = assembler.generate();
        //scenario.attach(img,"image/jpeg","Screenshot");
        scenario.attach(animation, "image/gif","Record");
        // }
        if(driver!=null){
            PageObjectManager.clearObjectAfterScenario(driver);
            driver.quit();
            driver = null;
        }
        Thread.sleep(1000);
    }

    @Then("user click on {string} on {string}")
    public void userClickOnOn(String elementName, String pageName) {
        ActionClass.clickOnElement(driver,pageName,elementName);
    }

    @And("validate element displayed {string} on {string}")
    public void displayelement(String elementName, String pageName) {
        WebElement element = PageObjectManager.getWebElement(driver,pageName,elementName);
        Assert.assertTrue(element.isDisplayed(),"Element is not displayed");
    }

    @Then("wait for {string} on {string}")
    public void waitForOn(String elementName, String pageName) throws InterruptedException {
        if(elementName.equals("pageLoad")) {
            Thread.sleep(1000);
            WebDriverWaitFactory.waitForpageLoad(driver,20);
        } else {
            WebElement ele = PageObjectManager.getWebElement(driver, pageName, elementName);
            WebDriverWaitFactory.waitForElement(driver, ele, 10);
            if (!ele.isDisplayed())
                Assert.fail("Element not displayed on page");
        }
    }

    @Then("user selects {string} value as {string} on {string}")
    public void userSelectsValueAsOn(String elementName, String option, String pageName) {
        ActionClass.selectOption(driver,pageName,elementName,option);
    }

    @Then("scroll in to view {string} on {string}")
    public void scrollInToViewOn(String elementName, String pageName) {
        ActionClass.scrollIntoView(driver,pageName,elementName);
    }

    @Then("user enters data for {string} as {string} on {string}")
    public void userEntersDataForAsOn(String elementName, String value, String pageName) {
        ActionClass.sendKeys(driver,pageName,elementName,value);
    }

    @And("validate {string} are displayed on {string}")
    public void validateAreDisplayedOn(String elementName, String pageName) {
        List<String> data = PageObjectManager.getTextListWebElement(driver,pageName,elementName);
        if(data.isEmpty()) {
            Assert.fail("Search result are not displayed");
        }
    }

    @And("validate {string} contains product name as {string} for atleast {string} products on {string}")
    public void validateContainsProductNameAsOn(String elementName, String productName, String count,String pageName) {
        List<String> actualdata = PageObjectManager.getTextListWebElement(driver,pageName,elementName);
        ArrayList<String> found = new ArrayList<>();
        actualdata.stream().forEach(y->{
            if(y.toLowerCase().contains(productName.toLowerCase())) {
                found.add(y);
            }
        });
        if(found.isEmpty() && found.size()<Integer.parseInt(count)) {
            Assert.fail("Searched result does not contain searched product name :: "+productName + " for atleast :: "+count);
        }
    }

    @And("validate {string} {string} loaded")
    public void validateLoaded(String pageName, String pageloadedorNot) {
        boolean loaded = PageObjectManager.pageLoaded(driver,pageName,pageName);
        if(pageloadedorNot.equalsIgnoreCase("is not") && loaded) {
            Assert.fail(pageName+" should not be loaded");
        } else if(pageloadedorNot.equalsIgnoreCase("is") && !loaded) {
            Assert.fail(pageName+" should be loaded");
        }
    }

    @And("validate {string} displayed has searched char as {string} on {string}")
    public void validateSuggestion_listDisplayedHasSearchedCharAsOn(String elementName,String expectedChar, String pageName) {
        List<String> actualdata = PageObjectManager.getTextListWebElement(driver,pageName,elementName);
        ArrayList<String> incorrectfound = new ArrayList<>();
        actualdata.stream().forEach(y->{
            if(!y.toLowerCase().startsWith(expectedChar.toLowerCase())) {
                incorrectfound.add(y);
            }
        });

        if(!incorrectfound.isEmpty())
            Assert.fail("Incorrect Suggestion provided :: "+incorrectfound.toString());
    }

    @AfterStep
    public void afterstep(Scenario scenario){
        String details = scenario.getName();
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        byte img[]=screenshot.getScreenshotAs(OutputType.BYTES);
        assembler.addFrame(details, img);
    }
}
