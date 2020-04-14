package com.nagp.common.runner;

import com.nagp.common.utilities.ConfigReader;
import com.nagp.common.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.nagp.common.reportlogger.ReportLogger;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseRunner {
    public static String projectPath = System.getProperty("user.dir");
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest logger;

    static ConfigReader readingPropertiesFile = new ConfigReader();

    static {
        String browserName = readingPropertiesFile.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            WebDriverManager.chromedriver().setup();
        } else if (browserName.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        String currentReportFolder = projectPath + "/CurrentReports";
        File[] directories = new File(currentReportFolder).listFiles(File::isDirectory);
        if (!(new File(currentReportFolder).exists()))
            new File(currentReportFolder).mkdir();
        else if (directories.length > 0) {
            new Utilities(driver).archieveLastReports(directories[0].getPath());
        }
        ReportLogger.reportFolder = projectPath + "/CurrentReports/Reports";
        ReportLogger.generateReport(ReportLogger.reportFolder + "/Report.html");
    }

    @BeforeTest
    public void beforeTest() throws Exception {
        driver.get(readingPropertiesFile.getProperty("URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod(Method method, ITestResult result) {
        ReportLogger.newTest(method.getName(), result);
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ReportLogger.fail(result);
        } else if (result.getStatus() == ITestResult.SUCCESS)
            ReportLogger.pass(result);
        else
            ReportLogger.skipped(result);
    }

    @AfterSuite
    public void tearDown() {
        ReportLogger.printReport();
        driver.quit();
    }
}