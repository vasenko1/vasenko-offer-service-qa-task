package com.steps_ui;

import com.page_object.NewOfferModal;
import com.models.OfferObjectModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewOfferModalSteps extends BaseUiSteps{
    private NewOfferModal _newOfferModal = new NewOfferModal(driver);
    protected NewOfferModalSteps(WebDriver driver) {
        super(driver);
    }

    public void FillAllFields(OfferObjectModel offer){
        FillOfferNameField(offer.name);
        FillOfferPrizeField(offer.prize);
        FillOfferDescriptionField(offer.description);
        ClickCreateButton();
    }

    public void ClickCreateButton(){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_newOfferModal.createButton));
        driver.findElement(_newOfferModal.createButton).click();
        System.out.println("[New offer modal] 'Create' button is clicked.");
    }

    public void FillOfferNameField(String offerName){
        driver.findElement(_newOfferModal.nameField).sendKeys(offerName);
        System.out.println("[New offer modal] Name value is set '" + offerName + "'.");
    }

    public void FillOfferPrizeField(String offerPrize){
        driver.findElement(_newOfferModal.prizeField).sendKeys(offerPrize);
        System.out.println("[New offer modal] Prize value is set: '" + offerPrize + "'.");
    }

    public void FillOfferDescriptionField(String offerDescription){
        driver.findElement(_newOfferModal.descriptionField).sendKeys(offerDescription);
        System.out.println("[New offer modal] Description value is set: '" + offerDescription + "'.");
    }
}