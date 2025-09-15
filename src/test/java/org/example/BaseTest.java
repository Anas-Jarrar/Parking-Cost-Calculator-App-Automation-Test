package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("C:\\Users\\User\\Automation\\AdBlock-—-block-ads-across-the-web-Chrome-Web-Store.crx");
//        File file =new File("C:\\Users\\User\\Automation\\AdBlock-—-block-ads-across-the-web-Chrome-Web-Store");
//        options.addExtensions(file);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }
    public void navigate(String url){
        driver.get(url);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
//       driver.quit();
    }
}
