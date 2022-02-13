package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PageObjectManager {
    private static ConcurrentHashMap<String, Object> pageObjectMap = new ConcurrentHashMap<>();

    public static Object getClassObject(WebDriver driver, String pageName) {
        try {
            Class clazz = Class.forName("pages." + pageName);
            Constructor cons = clazz.getConstructor(WebDriver.class);
            String sesssionId = String.valueOf(((RemoteWebDriver)driver).getSessionId());
            if(!pageObjectMap.containsKey(pageName+sesssionId))
                pageObjectMap.put(pageName+sesssionId,cons.newInstance(driver));
            return pageObjectMap.get(pageName+sesssionId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WebElement getWebElement(WebDriver driver,String pageName, String elementName) {
        try {
            Object obj = getClassObject(driver,pageName);
            Field f = obj.getClass().getDeclaredField(elementName);
            f.setAccessible(true);
            WebElement element = null;
            try {
                element = ((WebElement) f.get(obj));
            } catch (IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
            return element;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<WebElement> getListWebElement(WebDriver driver, String pageName, String elementName) {
        try {
            Object obj = getClassObject(driver,pageName);
            Field f = obj.getClass().getDeclaredField(elementName);
            f.setAccessible(true);
            List<WebElement> element = null;
            try {
                element = ((List<WebElement>) f.get(obj));
            } catch (IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
            return element;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<String> getTextListWebElement(WebDriver driver, String pageName, String elementName){
        List<WebElement> elements = getListWebElement(driver,pageName,elementName);
        WebDriverWaitFactory.waitForListElement(driver,elements,20);
        List<String>actuaData = new ArrayList<>();
        for(WebElement ele : elements){
            actuaData.add(ele.getText());
        }
        return actuaData;
    }

    public static String getTextWebElement(WebDriver driver, String pageName, String elementName){
        WebElement elements = getWebElement(driver,pageName,elementName);
        return elements.getText();
    }

    public static boolean pageLoaded(WebDriver driver, String pageName, String elementName){
        try {
            WebElement elements = getWebElement(driver, pageName, elementName);
            return elements.isDisplayed();
        }catch(Exception e) {
            return false;
        }
    }

    public static void clearObjectAfterScenario(WebDriver driver) {

        String sessionId = String.valueOf(((RemoteWebDriver)driver).getSessionId());
        Set<String> keys = pageObjectMap.keySet();
        for(String key: keys){
            if(key.contains(sessionId)){
                pageObjectMap.remove(key);
            }
        }
    }
}
