package com.steps_ui;

import com.page_object.EditModal;
import com.models.OfferObjectModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditModalSteps extends BaseUiSteps {
    private EditModal _editModal;

    public EditModalSteps(WebDriver driver) {
        super(driver);
        _editModal = new EditModal(driver);
    }

    public void EditAllOfferData(OfferObjectModel editedOffer){
        EditOfferName(editedOffer.name);
        EditOfferPrize(editedOffer.prize);
        EditOfferDescription(editedOffer.description);
        ClickSaveButton();
    }

    public void EditOfferName(String offerName){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_editModal.nameField));
        UpdateFieldValue(_editModal.nameField, offerName);
        System.out.println("[Edit modal] Name is set: '" + offerName + "'");
    }

    public void EditOfferPrize(String offerPrize){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_editModal.prizeField));
        UpdateFieldValue(_editModal.prizeField, offerPrize);
        System.out.println("[Edit modal] Prize is set: '" + offerPrize + "'");
    }

    public void EditOfferDescription(String offerDescription){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_editModal.descriptionField));
        UpdateFieldValue(_editModal.descriptionField, offerDescription);
        System.out.println("[Edit modal] Description is set: '" + offerDescription + "'");
    }

    public void ClickSaveButton(){
        driver.findElement(_editModal.saveButton).click();
        System.out.println("[Edit modal] 'Save' button is clicked.");
    }

    public String GetOfferDescription(){
        String fieldValue =  GetOfferField(_editModal.descriptionField);
        System.out.println("[Edit modal] Get Description: '" + fieldValue + "'");
        return fieldValue;
    }

    public String GetOfferName(){
        String fieldValue =  GetOfferField(_editModal.nameField);
        System.out.println("[Edit modal] Get Name: '" + fieldValue + "'");
        return fieldValue;
    }

    public String GetOfferPrize(){
        String fieldValue = GetOfferField(_editModal.prizeField);
        System.out.println("[Edit modal] Get Prize: '" + fieldValue + "'");
        return fieldValue;
    }

    private String GetOfferField(By locator){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return GetFieldValue(locator);
    }

    private void UpdateFieldValue(By targetField, String newFieldValue){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(targetField));
        driver.findElement(targetField).clear();
        driver.findElement(targetField).sendKeys(newFieldValue);
    }

    private String GetFieldValue(By targetField){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(targetField));
        return driver.findElement(targetField).getAttribute("value");
    }
}