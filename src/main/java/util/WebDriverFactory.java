package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class WebDriverFactory {
    private static Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    static String browserName;

    public static WebDriver get() {
        browserName = PropertyReader.getInstance().getProperty("browserName").toLowerCase();
        return get(browserName);
    }

    public static WebDriver get(String browser) {
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        System.setProperty("webdriver.chrome.driver","./src/test/resources/driver/chromedriver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
