package testCases.Tests;

import core.BaseAssert;
import core.ExecutionRetry;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.ReportUtils;

import java.lang.reflect.Method;

public class BaseTest {

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
    protected void beforeMethod(Method method) {
        try {
            ReportUtils.startExtentTest(method.getName(), method.getDeclaringClass().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult iTestResult) {
        ReportUtils.endExtentTest(iTestResult);
        BaseAssert.collectAsserts();
    }

    protected void endTest() {
        BaseAssert.collectAsserts();
    }
}