package rahulshettyacademy.testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import rahulshettyacademy.pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


public class BaseTest {

    public WebDriver driver;
    protected LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        //read the properties file
        FileInputStream propertiesFileInStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/rahulshettyacademy/resources/GlobalData.properties");
        Properties properties = new Properties();
        properties.load(propertiesFileInStream);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        if (browserName.contains("chrome")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            if (browserName.contains("headless")) {
                chromeOptions.addArguments("headless");
            }

            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browserName.contains("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            if (browserName.contains("headless")) {
                firefoxOptions.addArguments("headless");
            }

            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browserName.equalsIgnoreCase("safari")) {

            driver = new SafariDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else {
            System.out.println("Invalid browser name");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));

        return driver;
    }


    public List<HashMap<String, String>> getDataFromJson(String filePath) throws IOException {

        //convert json file to string
        String data = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath));
        //convert json string to hashmap
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> listOfData = objectMapper.readValue(data, new TypeReference<List<HashMap<String, String>>>() {
        });

        return listOfData;

    }


    public String takeScreenShot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        File screenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File screenShotDestination = new File(System.getProperty("user.dir") + "/src/main/java/rahulshettyacademy/resources/screenshots/" + testCaseName + ".png");
        FileUtils.copyFile(screenShot, screenShotDestination);

        return System.getProperty("user.dir") + "/src/main/java/rahulshettyacademy/resources/screenshots/" + testCaseName + ".png";

    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage navigateToUrl() throws IOException {

        initializeDriver();
        driver.get("https://rahulshettyacademy.com/client/");
        landingPage = new LandingPage(driver);
        return landingPage;

    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
