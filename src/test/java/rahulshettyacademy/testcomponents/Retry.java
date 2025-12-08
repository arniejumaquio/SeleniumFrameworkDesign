package rahulshettyacademy.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int retryCount = 0;
    int maxRetry = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (retryCount < maxRetry) {
            retryCount++;
            return true;
        }


        return false;

    }
}
