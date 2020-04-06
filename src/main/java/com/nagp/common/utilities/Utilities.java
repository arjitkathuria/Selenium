package com.nagp.common.utilities;

import com.google.common.base.Throwables;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Utilities extends FileUtility {

    private static final Logger LOGGER = Logger.getLogger(Utilities.class);

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public Utilities(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public boolean isElementExist(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            LOGGER.debug("Element is not visible : " + Throwables.getStackTraceAsString(e));
            return false;
        }
    }

    public void waitForElementExists(WebElement element, int timeInSeconds) {
        try {
            wait = new WebDriverWait(driver, timeInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LOGGER.debug("Unable to find the elements " + element + " due to following: " + Arrays.toString(e.getStackTrace()));
        }
    }

    public void moveToElement(WebElement element) {
        try {
            waitForElementExists(element, 10);
            actions.moveToElement(element).build().perform();
            LOGGER.info("Successfully move to element " + element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getScreenshotPath(String screenshotName) {
        String dateName = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss").format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    public String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void archieveLastReports(String src) throws IOException {
        createZipFolder(src);
        deleteFileFolder(new File(src));
        String archiveReportZip = System.getProperty("user.dir") + "/ArchivedReports/" + getCurrentDateTime() + ".zip";
        new File(System.getProperty("user.dir") + "/ArchivedReports").mkdir();
        copyFolder(new File(src + ".zip"), new File(archiveReportZip));
        deleteFileFolder(new File(src + ".zip"));
    }

    public void waitForElementsExists(List<WebElement> elements, int timeInSeconds) {
        try {
            wait = new WebDriverWait(this.driver, timeInSeconds);
            wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            LOGGER.debug("Unable to find the elements due to following: " + Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean isAllElementsDisplayed(List<WebElement> elements) {
        try {
            elements.stream().forEach(e -> e.isDisplayed());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
