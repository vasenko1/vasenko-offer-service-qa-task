package com.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditModal extends BaseModal{
    public By nameField = By.cssSelector("#name");
    public By prizeField = By.cssSelector("#prize");
    public By descriptionField = By.id("description");
    public By saveButton = By.cssSelector(".save-button");
    public By loadSpinner = By.cssSelector("svg.p-progress-spinner-svg");

    public EditModal(WebDriver driver) {
        super(driver);
    }
}
