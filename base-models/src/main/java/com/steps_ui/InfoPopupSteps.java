package com.steps_ui;

import com.page_object.InfoPopup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InfoPopupSteps extends BaseUiSteps{
    private InfoPopup _infoPopup = new InfoPopup(driver);
    public InfoPopupSteps(WebDriver driver) {
        super(driver);
    }

    public String GetOfferName(){
        _wait.until((ExpectedConditions.visibilityOfElementLocated(_infoPopup.nameField)));
        String nameValue = driver.findElement(_infoPopup.nameField).getText();
        System.out.println("[Info popup] Get Name value: '" + nameValue + "'.");
        return nameValue;
    }

    public String GetPrizeName(){
        _wait.until((ExpectedConditions.visibilityOfElementLocated(_infoPopup.prizeField)));
        String prizeValue = driver.findElement(_infoPopup.prizeField).getText();
        prizeValue = prizeValue.substring(0, prizeValue.indexOf(' '));
        System.out.println("[Info popup] Get Prize value: '" + prizeValue + "'.");
        return prizeValue;
    }

    public String GetDescriptionName(){
        _wait.until((ExpectedConditions.visibilityOfElementLocated(_infoPopup.descriptionField)));
        String descriptionValue = driver.findElement(_infoPopup.descriptionField).getText();
        System.out.println("[Info popup] Get Description value: '" + descriptionValue + "'.");
        return descriptionValue;
    }
}
