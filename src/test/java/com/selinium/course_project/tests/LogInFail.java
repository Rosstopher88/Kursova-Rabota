package com.selinium.course_project.tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.selinium.course_project.tests.base.TestBaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LogInFail extends TestBaseUtil {

    @BeforeMethod
    public void startNewSession() {
        setUpNewSession();
    }


    @AfterMethod
    public void tearDownSession() {
        driver.quit();
    }

    @DataProvider (name = "wrongUsersReadFromCsvFile")
    public Object [][] readWrongUsersFromCsv() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongUsersList.csv"))) {
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;
        }
    }

    @Test (dataProvider = "wrongUsersReadFromCsvFile")
    public void LoginWithWrongUser(String userName, String password){
        WebElement userNameInput = driver.findElement(By.xpath("//input[contains(@name,'name')]"));
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//input[starts-with(@name,'login')]"));
        loginButton.click();

        WebElement loginErrorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(loginErrorMessage.isDisplayed());
    }
}
