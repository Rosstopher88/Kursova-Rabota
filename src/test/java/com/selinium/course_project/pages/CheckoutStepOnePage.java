package com.selinium.course_project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutStepOnePage {
    protected WebDriver driver;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueSubmit;

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutStepTwoPage nameAndPostalCodeInput(){
        firstNameInput.clear();
        firstNameInput.sendKeys("Rosen");

        lastNameInput.clear();
        lastNameInput.sendKeys("Kostadinov");

        postalCodeInput.clear();
        postalCodeInput.sendKeys("1000");

        continueSubmit.click();

        return new CheckoutStepTwoPage(driver);
    }
}
