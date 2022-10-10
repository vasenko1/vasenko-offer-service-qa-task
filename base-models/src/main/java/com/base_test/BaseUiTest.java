package com.base_test;

import com.base.web_driver.BaseWebDriver;
import com.steps_ui.OfferPageSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseUiTest extends BaseApiTest{
    private BaseWebDriver _baseDriver = new BaseWebDriver();
    protected WebDriver driver;
    protected OfferPageSteps offerPageSteps;

    public BaseUiTest(){
        driver = _baseDriver.GetChromeDriver();
        offerPageSteps = new OfferPageSteps(driver);
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {driver.quit();}
}