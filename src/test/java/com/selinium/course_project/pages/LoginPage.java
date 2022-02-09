package com.selinium.course_project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.selinium.course_project.tests.base.TestBaseUtil;

public class LoginPage extends TestBaseUtil {

    @FindBy(name = "user-name")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        TestBaseUtil.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage login(String username, String password) {
        userNameInput.clear();
        userNameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginButton.click();

        return new ProductsPage(driver);
    }
}


