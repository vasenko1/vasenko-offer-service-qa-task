package com.lottoland.offer.integration;

import com.base_test.BaseApiTest;
import com.models.OfferDataStorage;
import com.models.OfferObjectModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

public class UpdateOfferTest extends BaseApiTest {
    // integration tests to validate offer modification via /v1/offer api

    @DisplayName("Validate Offer data from a single offer response.")
    @Test
    public void UpdateOffer(){
        //Arrange
        OfferObjectModel initialOffer = new OfferDataStorage().regularOffer;
        OfferObjectModel editedOffer = new OfferDataStorage().editedOffer;
        apiPutOfferSteps.CreateOffer(initialOffer);
        HttpResponse response = apiGetOfferSteps.SendGetOfferListRequest();
        String offerId = apiGetOfferSteps.GetOfferIdFromListByName(response, initialOffer.name);
        //Act
        apiPostOfferSteps.SendEditOfferRequest(editedOffer, offerId);
        //Assert
        OfferObjectModel receivedOffer = apiGetOfferSteps.GetParticularOfferById(offerId);
        Assertions.assertEquals(editedOffer.name, receivedOffer.name, "Offer Name is not updated!");
        Assertions.assertEquals(editedOffer.description, receivedOffer.description, "Offer Description is not updated!");
        Assertions.assertEquals(editedOffer.prize, receivedOffer.prize, "Offer Prize is not updated!");
    }
}