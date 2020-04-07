package com.nagp.common.reportlogger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nagp.common.runner.BaseRunner;
import com.nagp.common.utilities.Utilities;
import org.apache.log4j.Logger;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/***
 * Function Decription:- Function to add result in extent report for pass,fail,skipped.
 * Created by - Arjit Kathuria
 */

public class ReportLogger extends BaseRunner {
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest extentTest;
    public static final Logger LOGGER = Logger.getLogger(ReportLogger.class);
    public static String reportFolder;
    public static String archiveFolder;

    public static void generateReport(String reportPath) throws IOException {
        new File(ReportLogger.reportFolder).mkdir();
        htmlReporter = new ExtentHtmlReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Repot");
        htmlReporter.config().setReportName("NAGP Assignment");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("yyyy-MM-dd-HH:mm:ss");
        LOGGER.info("HTML report created : ");
    }

    public static void info(String msg) {
        extentTest.log(Status.INFO, msg);
        LOGGER.info(msg);
    }

    public static void error(String msg) {
        extentTest.log(Status.ERROR, msg);
        LOGGER.error(msg);

    }

    public static void pass(ITestResult result) {
        extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        LOGGER.info(result.getName() + " PASSED ");
    }

    public static void fail(ITestResult result) {
        extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
        extentTest.fail(result.getThrowable());
        LOGGER.error(result.getName() + " FAILED ");
        StringWriter sw = new StringWriter();
        result.getThrowable().printStackTrace(new PrintWriter(sw));
        LOGGER.error(sw.toString());
        sw = null;
        try {
            String path = new Utilities(driver).getScreenshotPath(result.getName());
            extentTest.addScreenCaptureFromPath(path);
        } catch (IOException e) {
            LOGGER.info("Failed to upload Screenshot in extent report");
        }
    }

    public static void skipped(ITestResult result) {
        extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
        extentTest.skip(result.getThrowable());
        LOGGER.error(result.getName() + " SKIPPED ");
    }

    public static void printReport() {
        extent.flush();
        LOGGER.info("HTML report saved at " + System.getProperty("user.dir") + "/CurrentReports/Reports/Report.html");
    }

    public static void newTest(String method, ITestResult result) {
        extentTest = extent.createTest(method);
        LOGGER.info("New testcase :" + method);
        ReportLogger.info("Testcase Description : " + result.getMethod().getDescription());
    }
}
