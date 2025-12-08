package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtility {

    public static ExtentReports getExtentReports() {

        String reportsPath = System.getProperty("user.dir")+"/reports";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportsPath);
        extentSparkReporter.config().setReportName("Regression Suite");
        extentSparkReporter.config().setDocumentTitle("Test Results");


        ExtentReports  extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("QE","Arnie");

        return extentReports;

    }

}
