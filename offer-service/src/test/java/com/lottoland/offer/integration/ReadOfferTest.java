package com.lottoland.offer.integration;

import com.base_test.BaseApiTest;
import com.models.OfferDataStorage;
import com.models.OfferObjectModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

public class ReadOfferTest extends BaseApiTest {
    // integration tests to validate offer info via /v1/offer api

    @DisplayName("Validate Offer data from offer list.")
    @Test
    public void ReadOfferFromList(){
        //Arrange
        OfferObjectModel offer = new OfferDataStorage().regularOffer;
        apiPutOfferSteps.CreateOffer(offer);
        //Act
        HttpResponse response = apiGetOfferSteps.SendGetOfferListRequest();
        OfferObjectModel receivedOffer = apiGetOfferSteps.GetOfferFromListByName(response, offer.name);
        //Assert
        Assertions.assertEquals(offer.name, receivedOffer.name, "Offer Name value is wrong.");
        Assertions.assertEquals(offer.prize, receivedOffer.prize, "Offer Prize value is wrong.");
        Assertions.assertEquals(offer.description, receivedOffer.description, "Offer Description value is wrong.");
    }

    @DisplayName("Validate Offer data from a single offer response.")
    @Test
    public void ReadSingleOffer(){
        //Arrange
        OfferObjectModel offer = new OfferDataStorage().regularOffer;
        apiPutOfferSteps.CreateOffer(offer);
        HttpResponse response = apiGetOfferSteps.SendGetOfferListRequest();
        String offerId = apiGetOfferSteps.GetOfferIdFromListByName(response, offer.name);
        //Act
        OfferObjectModel receivedOffer = apiGetOfferSteps.GetParticularOfferById(offerId);
        //Assert
        Assertions.assertEquals(offer.name, receivedOffer.name, "Offer Name value is wrong.");
        Assertions.assertEquals(offer.prize, receivedOffer.prize, "Offer Prize value is wrong.");
        Assertions.assertEquals(offer.description, receivedOffer.description, "Offer Description value is wrong.");
    }
}