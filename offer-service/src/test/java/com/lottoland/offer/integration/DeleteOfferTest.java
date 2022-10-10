package com.lottoland.offer.integration;

import com.base_test.BaseApiTest;
import com.models.OfferDataStorage;
import com.models.OfferObjectModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

public class DeleteOfferTest extends BaseApiTest {
    // integration tests to validate offer removal via /v1/offer api

    @DisplayName("Check if offer is deleted successfully.")
    @Test
    public void DeleteSingleOffer(){
        //Arrange
        OfferObjectModel initialOffer = new OfferDataStorage().regularOffer;
        apiPutOfferSteps.CreateOffer(initialOffer);
        HttpResponse response = apiGetOfferSteps.SendGetOfferListRequest();
        String offerId = apiGetOfferSteps.GetOfferIdFromListByName(response, initialOffer.name);
        //Act
        apiDeleteOfferSteps.DeleteOfferById(offerId);
        response = apiGetOfferSteps.GetParticularOfferResponseById(offerId);
        //Assert
        Assertions.assertTrue(response.body().toString().contains(offerId + " not found"),
                "Expected Offer is deleted.");
    }

    @DisplayName("Check if OfferId value is unique.")
    @Test
    public void CheckOfferIdsNotEquals(){
        //Arrange
        OfferObjectModel offer1 = new OfferDataStorage().regularOffer; //Create offer object #1
        OfferObjectModel offer2 = new OfferDataStorage().regularOffer; //Create offer object #2
        OfferObjectModel offer3 = new OfferDataStorage().regularOffer; //Create offer object #3
        apiPutOfferSteps.CreateOffer(offer1);
        apiPutOfferSteps.CreateOffer(offer2);
        HttpResponse response = apiGetOfferSteps.SendGetOfferListRequest();
        String expectedOfferId1 = apiGetOfferSteps.GetOfferIdFromListByName(response, offer1.name);
        String expectedOfferId2 = apiGetOfferSteps.GetOfferIdFromListByName(response, offer2.name);
        apiDeleteOfferSteps.DeleteOfferById(expectedOfferId1);
        //Act
        apiPutOfferSteps.CreateOffer(offer3);
        response = apiGetOfferSteps.SendGetOfferListRequest();
        String expectedOfferId3 = apiGetOfferSteps.GetOfferIdFromListByName(response, offer3.name);
        //Assert
        Assertions.assertNotEquals(expectedOfferId2, expectedOfferId3,
                "Offer Id duplication is detected for names: '"+ offer2.name +"' AND'"+ offer3.name +"'.");
    }
}
