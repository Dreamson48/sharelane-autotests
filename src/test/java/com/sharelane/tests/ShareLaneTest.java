package com.sharelane.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShareLaneTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testRegistrationFlow() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.linkText("Sign up")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("register.py"));
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        boolean isRegisterButtonDisplayed = driver.findElement(By.cssSelector("input[value='Register']")).isDisplayed();
        Assert.assertTrue(isRegisterButtonDisplayed);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}