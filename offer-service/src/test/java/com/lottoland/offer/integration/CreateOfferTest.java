package com.lottoland.offer.integration;

import com.base_test.BaseApiTest;
import com.models.OfferDataStorage;
import com.models.OfferObjectModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

public class CreateOfferTest extends BaseApiTest {
    // integration tests to validate offer creation via /v1/offer api

    @DisplayName("Check if offer is creating.")
    @Test
    public void CheckStatusForValidOfferData(){
        //Arrange
        OfferObjectModel offer = new OfferDataStorage().regularOffer;
        //Act
        HttpResponse response = apiPutOfferSteps.CreateOffer(offer);
        //Assert
        Assertions.assertEquals(response.statusCode(), 200,  "Expected Status code 200 (OK)!");
    }

    @DisplayName("Check if offer is not created by empty data.")
    @Test
    public void CheckOfferCreationByEmptyOfferData(){
        //Arrange
        OfferObjectModel offer = new OfferDataStorage().emptyOffer;
        //Act
        HttpResponse response = apiPutOfferSteps.CreateOffer(offer);
        //Assert
        Assertions.assertEquals(response.statusCode(), 400,  "Expected Status code 400 (Bad Request)!");
    }
}
