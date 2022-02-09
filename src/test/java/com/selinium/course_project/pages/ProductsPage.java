package com.selinium.course_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    protected WebDriver driver;
    public static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-sauce-labs-%s']";

    @FindBy(css = "[class='shopping_cart_container']")
    private WebElement shoppingCartContainerLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToCartByProductName(String productName){
        String xpathOfItemToAdd = String.format(ADD_TO_CART_LOCATOR, productName);
        WebElement addToCartButton = driver.findElement(By.xpath(xpathOfItemToAdd));
        addToCartButton.click();
    }
    public int getNumbersInCart(){
        return Integer.parseInt(shoppingCartBadge.getText());
    }

    public CartPage openCart() {
        shoppingCartContainerLink.click();
        return new CartPage(driver);
    }
}
