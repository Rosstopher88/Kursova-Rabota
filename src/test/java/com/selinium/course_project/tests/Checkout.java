package com.selinium.course_project.tests;

import com.selinium.course_project.pages.LoginPage;
import com.selinium.course_project.pages.ProductsPage;
import com.selinium.course_project.tests.base.TestBaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Checkout extends TestBaseUtil {

    @BeforeMethod
    public void startNewSession() {
        setUpNewSession();
    }


    @AfterMethod
    public void tearDownSession() {
        driver.quit();
    }

    @Test
    public void checkoutItemInCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCartByProductName("bike-light");

        WebElement shoppingCartContainerLink = driver.findElement(By.className("shopping_cart_container"));
        shoppingCartContainerLink.click();

        WebElement checkoutButton = driver.findElement(By.name("checkout"));
        checkoutButton.click();

        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        firstNameInput.sendKeys("Rosen");

        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        lastNameInput.sendKeys("Kostadinov");

        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
        postalCodeInput.sendKeys("1000");

        WebElement continueButton = driver.findElement(By.className("submit-button"));
        continueButton.click();

        WebElement finishButton = driver.findElement(By.name("finish"));
        finishButton.click();

        WebElement ponyExpressLogo = driver.findElement(By.xpath("//span[text()='Checkout: Complete!']"));
        Assert.assertTrue(ponyExpressLogo.isDisplayed());

    }
}
