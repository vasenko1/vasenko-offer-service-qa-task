package com.steps_ui;

import com.page_object.BasePage;
import com.page_object.OfferPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseUiSteps extends BasePage {
    private final OfferPage _offerPage;
    protected WebDriverWait _wait;

    protected BaseUiSteps(WebDriver driver){
        super(driver);
        _wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        _offerPage = new OfferPage(driver);
    }

    protected void RefreshPage(){
        driver.navigate().refresh();
        _wait.until((ExpectedConditions.visibilityOfElementLocated(_offerPage.createOfferButton)));
        System.out.println("The page is refreshed '" + driver.getCurrentUrl() + "'.");
    }
}
