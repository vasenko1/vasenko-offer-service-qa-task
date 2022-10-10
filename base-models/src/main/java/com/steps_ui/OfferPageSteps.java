package com.steps_ui;

import com.models.OfferObjectModel;
import com.page_object.*;
import org.apache.http.client.utils.URIBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OfferPageSteps extends BaseUiSteps{
    private InfoPopupSteps _infoPopupSteps = new InfoPopupSteps(driver);
    private InfoPopup _infoPopup = new InfoPopup(driver);
    private NewOfferModal _newOfferModal = new NewOfferModal(driver);
    private EditModal _editModal = new EditModal(driver);
    private NewOfferModalSteps _newOfferModalSteps = new NewOfferModalSteps(driver);
    private OfferPage _offerPage = new OfferPage(driver);
    private FileInputStream _fileInputStream;
    private Properties _prop = new Properties();
    private EditModalSteps _editModalSteps = new EditModalSteps(driver);
    public OfferPageSteps(WebDriver driver) {
        super(driver);
        try {
            String filePath = new File("config.properties").getAbsolutePath();
            _fileInputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        _prop = GetProperties(_fileInputStream);
    }

    public OfferObjectModel GetOfferDataFromInfoPopup(){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_infoPopup.nameField));
        OfferObjectModel receivedOfferInfo = new OfferObjectModel() {{
            name = _infoPopupSteps.GetOfferName();
            prize =  _infoPopupSteps.GetPrizeName();
            description = _infoPopupSteps.GetDescriptionName();
        }};
        //_infoPopup.ClickCloseButton();
        return receivedOfferInfo;
    }

    public void OpenPage(){
        String offerPageUrl = GetOfferPageUrl();
        driver.navigate().to(offerPageUrl);
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_offerPage.tableBody));
        System.out.println("Offer page is opened by URL: '" + offerPageUrl + "'.");
    }

    public void CreateNewOffer(OfferObjectModel offer){
        ClickCreateOfferButton();
        _newOfferModalSteps.FillAllFields(offer);
        _wait.until(ExpectedConditions.invisibilityOfElementLocated(_newOfferModal.loadSpinner));
    }

    public void UpdateOfferName(OfferObjectModel targetOffer, OfferObjectModel newOfferData){
        ClickRowEditButton(targetOffer.name);
        _editModalSteps.EditOfferName(newOfferData.name);
        _editModalSteps.ClickSaveButton();
        _wait.until(ExpectedConditions.invisibilityOfElementLocated(_editModal.loadSpinner));
    }

    public void UpdateOfferPrize(OfferObjectModel currentOffer, OfferObjectModel newOfferData){
        ClickRowEditButton(currentOffer.name);
        _editModalSteps.EditOfferPrize(newOfferData.prize);
        _editModalSteps.ClickSaveButton();
        _wait.until(ExpectedConditions.invisibilityOfElementLocated(_editModal.loadSpinner));
    }

    public void UpdateOfferDescription(OfferObjectModel currentOffer, OfferObjectModel newOfferData){
        ClickRowEditButton(currentOffer.name);
        _editModalSteps.EditOfferDescription(newOfferData.description);
        _editModalSteps.ClickSaveButton();
        _wait.until(ExpectedConditions.invisibilityOfElementLocated(_editModal.loadSpinner));
    }

    public OfferObjectModel GetOfferDataFromEditModal(){
        _editModal = new EditModal(driver);
        OfferObjectModel receivedData = new OfferObjectModel(){{
            name = _editModalSteps.GetOfferName();
            prize = _editModalSteps.GetOfferPrize();
            description = _editModalSteps.GetOfferDescription();
        }};
        return receivedData;
    }

    public void ClickRowEditButton(String offerName){
        OfferTableRow targetRow = GetTableRowByName(offerName);
        targetRow.ClickEditButton();
        System.out.println("'Edit' button is clicked on a row with name '" + offerName + "'.");
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_editModal.nameField));
    }

    public void ClickRowInfoButton(String offerName){
        OfferTableRow targetRow = GetTableRowByName(offerName);
        targetRow.ClickInfoButton();
        System.out.println("'Info' button is clicked on a row with name '" +  offerName + "'.");
    }

    public void ClickRowDeleteButton(String targetOfferName){
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_offerPage.tableBody));
        OfferTableRow targetRow = GetTableRowByName(targetOfferName);
        targetRow.ClickDeleteButton();
        System.out.println("'Delete' button is clicked on a row with name '" + targetOfferName + "'.");
        driver.navigate().refresh();
    }

    public boolean IsTableRowExist(String wantedRowName){
        List<OfferTableRow> tableRows = GetTableRows();
        for (OfferTableRow row : tableRows){
            String rowText = row.GetRowName();
            if(rowText.equals(wantedRowName)){
                System.out.println("Table row is exist: " + wantedRowName);
                return true;
            }
        }
        System.out.println("Table row is not exist: " + wantedRowName);
        return false;
    }

    private List<OfferTableRow> GetTableRows(){
        List<OfferTableRow> _tableRows = new ArrayList<>();

        List<WebElement> rows = driver.findElements(_offerPage.tableRow);
        for (WebElement row : rows) {
            _tableRows.add(new OfferTableRow(row));
        }
        return _tableRows;
    }

    private void ClickCreateOfferButton(){
        driver.findElement(_offerPage.createOfferButton).click();
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_newOfferModal.closeButton));
        System.out.println("'Create offer' button is clicked.");
    }

    private OfferTableRow GetTableRowByName(String rowName){
        List<OfferTableRow> tableRows = GetTableRows();
        for (OfferTableRow row : tableRows){
            String rowText = row.GetRowName();
            if(rowText.equals(rowName)){
                return row;
            }
        }
        throw new RuntimeException("There is no table row with name: " + rowName);
    }

    private String GetOfferPageUrl(){
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme(_prop.getProperty("uiUrlScheme"))
                    .setHost(_prop.getProperty("uiUrlHost"))
                    .setPort(Integer.parseInt(_prop.getProperty("uiUrlPort")))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return uri.toString();
    }

    private Properties GetProperties(FileInputStream fileInputStream){
        try {
            _prop.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return _prop;
    }
}