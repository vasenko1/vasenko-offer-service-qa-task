package com.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OfferTableRow {
    private By _rowName = By.cssSelector("td:first-child");
    private By _infoButton = By.cssSelector("button[aria-label='Info']");
    private By _editButton = By.cssSelector("button[aria-label='Edit']");
    private By _deleteButton = By.cssSelector("button[aria-label='Delete']");
    private WebElement _rowWebElement;

    public OfferTableRow(WebElement rowWebElement){
        _rowWebElement = rowWebElement;
    }

    public String GetRowName(){
        return _rowWebElement.findElement(_rowName).getText();
    }

    public void ClickEditButton(){
        _rowWebElement.findElement(_editButton).click();
    }

    public void ClickInfoButton(){
        _rowWebElement.findElement(_infoButton).click();
    }

    public void ClickDeleteButton(){
        _rowWebElement.findElement(_deleteButton).click();
    }
}