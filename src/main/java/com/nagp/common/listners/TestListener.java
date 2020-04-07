package com.nagp.common.listners;


import com.relevantcodes.extentreports.ExtentReports;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.nagp.common.reportlogger.ReportLogger;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = Logger.getLogger(TestListener.class);

    public static ExtentReports extent;

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stu
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ReportLogger.newTest(result.getName(),result);
        ReportLogger.info("Testcase get skipped");
        ReportLogger.skipped(result);
        LOGGER.debug("Test case skipped" + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
    }
}
