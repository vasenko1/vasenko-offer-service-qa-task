package com.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewOfferModal extends BaseModal{
    public By nameField = By.cssSelector("#name");
    public By prizeField = By.cssSelector("#prize");
    public By descriptionField = By.cssSelector("#description");
    public By createButton = By.cssSelector(".create-button");
    public By loadSpinner = By.cssSelector("svg.p-progress-spinner-svg");

    public NewOfferModal(WebDriver driver) {
        super(driver);
    }
}