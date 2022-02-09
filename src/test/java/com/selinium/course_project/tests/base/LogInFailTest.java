package com.selinium.course_project.tests.base;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.selinium.course_project.pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class LogInFailTest extends TestBaseUtil {


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
    public void unsuccessfulLoginWithWrongUsers1(String userName, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
    }
}
