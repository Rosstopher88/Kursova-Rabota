package com.selinium.course_project.tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.selinium.course_project.tests.base.TestBaseUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.selinium.course_project.pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginTest extends TestBaseUtil {

    @BeforeMethod
    public void startNewSession() {
        setUpNewSession();
    }

    @AfterMethod
    public void tearDownSession() {
        driver.quit();
    }

    @DataProvider(name = "wrongUsersReadFromCsvFile")
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

    @Test(dataProvider = "wrongUsersReadFromCsvFile")
    public void LoginWithWrongUsers(String userName, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
    }

}
