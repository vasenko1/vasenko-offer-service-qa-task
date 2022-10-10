package com.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseModal extends BasePage{
    public By closeButton = By.cssSelector("button.p-link[aria-label='Close']");

    protected BaseModal(WebDriver driver) {
        super(driver);
    }
}