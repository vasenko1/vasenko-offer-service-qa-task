package com.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OfferPage extends BasePage{
    public By createOfferButton = By.cssSelector("button[class*='p-component']");
    public By tableBody = By.cssSelector("tbody.p-datatable-tbody");
    public By tableRow = By.cssSelector("tbody>tr[role='row']");

    public OfferPage(WebDriver driver) {
        super(driver);
    }
}