package rahulshettyacademy.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.testcomponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class FileUploadTest extends BaseTest {

    @Test
    public void fileUploadTest() throws IOException, InterruptedException {

        String downloadPath = System.getProperty("user.dir") + "/src/main/java/rahulshettyacademy/resources/downloads";

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-primary")));

        WebElement downloadButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
        downloadButton.click();

        File downloadedFile = new File(downloadPath + "/samplefile.pdf");
        while (!downloadedFile.exists()) {

            //wait
            Thread.sleep(3000);

            if (downloadedFile.exists()) {
                break;
            }

        }

        System.out.println("File found");
        Assert.assertTrue(true);


    }

}
