package com.selinium.course_project.tests.base;

import com.selinium.course_project.driver.DriverFactory;
import com.selinium.course_project.pages.LoginPage;
import com.selinium.course_project.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parallel {

    WebDriver driver2;
    WebDriver driver3;

    private String applicationUrl, targetBrowser;
    private int implicitWaitSeconds, explicitWaitSeconds;

    @Test
    public void addItemToCart() {
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
        driver2 = DriverFactory.setupChromeDriver(implicitWaitSeconds);
        driver2.get(applicationUrl);
        LoginPage loginPage = new LoginPage(driver2);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        String xpathOfItemToAdd = String.format("//button[@id='add-to-cart-sauce-labs-%s']", "bike-light");
        WebElement addToCartButton = driver2.findElement(By.xpath(xpathOfItemToAdd));
        addToCartButton.click();
        String className;
        Assert.assertEquals(driver2.findElement(By.className("shopping_cart_badge")).getText(), "1", "1 items was added in cart");
        driver2.quit();
    }

    @Test
    public void addItemToCart2() {
        driver3 = DriverFactory.setupChromeDriver(4);
        driver3.get(applicationUrl);

        LoginPage loginPage = new LoginPage(driver3);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        String xpathOfItemToAdd = String.format("//button[@id='add-to-cart-sauce-labs-%s']", "bike-light");
        WebElement addToCartButton = driver3.findElement(By.xpath(xpathOfItemToAdd));
        addToCartButton.click();
        Assert.assertEquals(driver3.findElement(By.className("shopping_cart_badge")).getText(), "1", "1 items was added in cart");
        driver3.quit();
    }
}
