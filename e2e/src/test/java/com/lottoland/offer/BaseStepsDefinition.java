package com.lottoland.offer;

import com.base.web_driver.BaseWebDriver;
import com.base_test.BaseApiTest;
import com.steps_api.GetOfferSteps;
import com.steps_api.PutOfferSteps;
import com.steps_ui.OfferPageSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class BaseStepsDefinition extends BaseApiTest {
    public BaseWebDriver _baseWebDriver = new BaseWebDriver();
    protected static WebDriver driver;
    protected static OfferPageSteps offerPageSteps;
    protected static PutOfferSteps apiPutOfferSteps = new PutOfferSteps();
    protected static GetOfferSteps apiGetOfferSteps = new GetOfferSteps();

    @Before
    public void beforeScenario() {
        driver = _baseWebDriver.GetChromeDriver();
        offerPageSteps = new OfferPageSteps(driver);
    }
    @After
    public void cleanUp(){
        driver.quit();
        System.out.println("==================");
        System.out.println("Browser is closed.");
        System.out.println("==================");
    }
}