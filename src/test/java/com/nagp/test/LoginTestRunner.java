package com.nagp.test;

import com.nagp.common.Runner.BaseRunner;
import com.nagp.common.pages.common.Header;
import com.nagp.common.pages.HomePage;
import com.nagp.common.pages.LoginPage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;

public class LoginTestRunner extends BaseRunner {

    private HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    private LoginPage logionPage = PageFactory.initElements(driver, LoginPage.class);
    private Header header = PageFactory.initElements(driver, Header.class);

    @DataProvider(name = "LoginSheet")
    public Object[][] readData() throws Exception {
        FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "/TestData/TestDataSheet.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("TestData");
        Object loginData[][] = new Object[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                loginData[i][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
        return loginData;
    }

    @Test(description = "Verify login with valid user and invalid user", priority = 1, dataProvider = "LoginSheet")
    public void testData(String userName, String password) {
        SoftAssert softAssertion= new SoftAssert();
        homePage.clicklOnSignInButton();
        logionPage.login(userName, password);
        softAssertion.assertTrue(header.isAccontNameDisplayed(),
                String.format("Price not similar on page %s", header.getCurrentUrl()));;
        header.logout();
        Assert.assertTrue(header.isAccontNameDisplayed(),
                String.format("Price not similar on page %s", header.getCurrentUrl()));;
        header.logout();
    }

}
