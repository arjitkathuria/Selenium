package com.nagp.common.Runner;

import com.nagp.common.utilities.ConfigReader;
import com.nagp.common.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

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
            //System.setProperty("webdriver.chrome.driver", readingPropertiesFile.getProperty("driverPath"));
        } else if (browserName.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
            System.setProperty("webdriver.ie.driver", readingPropertiesFile.getProperty("driverPath"));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            System.setProperty("webdriver.gecko.driver", readingPropertiesFile.getProperty("driverPath"));
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
        extent = new ExtentReports(projectPath + "/CurrentReports/Reports/Report.html", true);
        extent.addSystemInfo("Host Name", "NAGP")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "Arjit Kathuria");
        extent.loadConfig(new File(projectPath + "/src/main/resources/reportConfig.xml"));
    }

    @BeforeTest
    public void beforeTest() throws Exception {
        driver.get(readingPropertiesFile.getProperty("URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger = extent.startTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS)
            logger.log(LogStatus.PASS, "Test case got passed");
        else if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, result.getThrowable());
            String screenshotPath = new Utilities(driver).getScreenshotPath(result.getName());
            logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
        } else if (result.getStatus() == ITestResult.SKIP)
            logger.log(LogStatus.SKIP, result.getThrowable());
        extent.flush();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}