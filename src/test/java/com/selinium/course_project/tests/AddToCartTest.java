package com.selinium.course_project.tests;

import com.selinium.course_project.tests.base.TestBaseUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.selinium.course_project.pages.LoginPage;
import com.selinium.course_project.pages.ProductsPage;

public class AddToCartTest extends TestBaseUtil {

    @BeforeMethod
    public void startNewSession() {
        setUpNewSession();
    }


    @AfterMethod
    public void tearDownSession() {
        driver.quit();
    }

    @Test
    public void addItemToCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCartByProductName("bike-light");
        productsPage.addItemToCartByProductName("onesie");
        productsPage.addItemToCartByProductName("bolt-t-shirt");

        Assert.assertEquals(productsPage.getNumbersInCart(), 3, "3 items was added in cart");
    }

}