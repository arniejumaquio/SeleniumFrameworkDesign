package rahulshettyacademy.cucumber_tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/rahulshettyacademy/cucumber_tests", glue = "rahulshettyacademy.cucumber_stepdefinitions", monochrome = true, tags = "@ErrorValidation", plugin = {"html:target/cucumber-html-report.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {


}
