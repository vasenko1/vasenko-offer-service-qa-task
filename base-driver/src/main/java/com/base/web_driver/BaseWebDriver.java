package com.base.web_driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;


public class BaseWebDriver {
    public BaseWebDriver(){
        WebDriverManager.chromedriver().setup();
    }

    public WebDriver GetChromeDriver(){
        SetupChromeDriver();
        return _driver;
    }

    private WebDriver _driver;

    private void SetupChromeDriver(){
        _driver = new ChromeDriver();
        _driver.manage().window().maximize();
        _driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("=========================");
        System.out.println("Chrome browser is opened.");
        System.out.println("=========================");
    }
}