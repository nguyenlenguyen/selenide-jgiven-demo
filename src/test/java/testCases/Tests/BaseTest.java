package testCases.Tests;

import core.BaseAssert;
import core.Browser;
import core.DriverManager;
import core.ExecutionRetry;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ReportUtils;

import java.lang.reflect.Method;

public class BaseTest {
    protected String[] publicationList = {};

    @BeforeSuite(alwaysRun = true)
    protected void createReport(ITestContext iTestContext) {
        if (iTestContext.getAllTestMethods().length > 1) {
            ReportUtils.createExtentReport(iTestContext.getSuite().getName());
            for (ITestNGMethod method : iTestContext.getAllTestMethods())
                method.setRetryAnalyzer(new ExecutionRetry());
        }
    }

    @AfterSuite(alwaysRun = true)
    protected void finalizeReport() {
        ReportUtils.finalizeExtentReport();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "device", "orientation"})
    protected void beforeMethod(String browser, String device, String orientation, Method method) {
        try {
            DriverManager.initDriver(browser, null, device, orientation);
            ReportUtils.startExtentTest(method.getName(), method.getDeclaringClass().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult iTestResult) {
        Browser.clearLocalStorage();
        DriverManager.getDriver().quit();
        ReportUtils.endExtentTest(iTestResult);
        BaseAssert.collectAsserts();
    }

    protected void endTest() {
        BaseAssert.collectAsserts();
    }
}