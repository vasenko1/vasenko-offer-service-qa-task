package com.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InfoPopup extends BaseModal{
    public By nameField = By.id("info-modal_header");
    public By prizeField = By.cssSelector("div p:first-of-type");
    public By descriptionField = By.cssSelector("div p:nth-of-type(2)");

    public InfoPopup(WebDriver driver) {
        super(driver);
    }
}