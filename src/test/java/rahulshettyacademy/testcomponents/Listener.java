package rahulshettyacademy.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import rahulshettyacademy.resources.ReportUtility;

import java.io.IOException;

public class Listener extends BaseTest implements ITestListener {

    ExtentReports extentReports = ReportUtility.getExtentReports();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {

        extentTest = extentReports.createTest(iTestResult.getMethod().getMethodName());
        threadLocal.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        threadLocal.get().log(Status.PASS, "Test Pass!");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        threadLocal.get().fail(iTestResult.getThrowable());

        try {
            driver = (WebDriver) iTestResult.getTestClass()
                    .getRealClass()
                    .getField("driver").
                    get(iTestResult.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        String screenShotPath = null;
        try {
            screenShotPath = takeScreenShot(iTestResult.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //add the screenshot
        threadLocal.get().addScreenCaptureFromPath(screenShotPath, iTestResult.getMethod().getMethodName());


    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("On Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
    }


}
