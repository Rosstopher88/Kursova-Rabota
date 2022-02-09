package com.selinium.course_project.tests.base;

import com.selinium.course_project.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBaseUtil {
    public static WebDriver driver;
    private String applicationUrl, targetBrowser;
    private int implicitWaitSeconds, explicitWaitSeconds;


    public void setUpNewSession() {
        setupBrowserDriver();
        loadPageUrl();
    }

    public void loadPageUrl() {
        driver.get(applicationUrl);
    }

    public void setupBrowserDriver() {
        try {
            FileInputStream configuration = new FileInputStream("src/test/resources/configuration.properties");
            Properties configProperties = new Properties();
            configProperties.load(configuration);
            applicationUrl = configProperties.getProperty("url");
            targetBrowser = configProperties.getProperty("browser");
            implicitWaitSeconds = Integer.parseInt(configProperties.getProperty("implicitWait"));
            explicitWaitSeconds = Integer.parseInt(configProperties.getProperty("explicitWait"));

        } catch (IOException e) {
            System.out.println(e);
        }

        switch (targetBrowser) {
            case "chrome":
                driver = DriverFactory.setupChromeDriver(implicitWaitSeconds);
                break;
            case "firefox":
                driver = DriverFactory.setupFirefoxDriver(implicitWaitSeconds);
                break;
            case "edge":
                driver = DriverFactory.setupEdgeDriver(implicitWaitSeconds);
                break;
            default:
                Assert.fail("Wrong browser name " + targetBrowser);
        }
    }

//    private WebDriver getChromeDriver(){
//        com.selinium.course_project.driver = DriverFactory.setupChromeDriver(implicitWaitSeconds);
//        return com.selinium.course_project.driver;
//    }
//
//    private WebDriver getFirefoxDriver(){
//        com.selinium.course_project.driver = DriverFactory.setupFirefoxDriver(implicitWaitSeconds);
//        return com.selinium.course_project.driver;
//    }
//
//    private WebDriver getEdgeDriver(){
//        com.selinium.course_project.driver = DriverFactory.setupEdgeDriver(implicitWaitSeconds);
//        return com.selinium.course_project.driver;
//    }
}
