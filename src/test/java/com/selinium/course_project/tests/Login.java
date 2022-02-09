package com.selinium.course_project.tests;

import com.selinium.course_project.tests.base.TestBaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends TestBaseUtil {

    @BeforeMethod
    public void startNewSession() {
        setUpNewSession();
    }


    @AfterMethod
    public void tearDownSession() {
        driver.quit();
    }


    @Test
    public void successfulLogin(){

        WebElement userNameInput = driver.findElement(By.cssSelector("[name=user-name]"));
        userNameInput.clear();
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.name("login-button"));
        loginButton.click();

        WebElement productsSortingDropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Assert.assertTrue(productsSortingDropdown.isDisplayed());

        WebElement productsPageTitle = driver.findElement(By.xpath("//span[text()='Products']"));
        Assert.assertTrue(productsPageTitle.isDisplayed());
    }
}
