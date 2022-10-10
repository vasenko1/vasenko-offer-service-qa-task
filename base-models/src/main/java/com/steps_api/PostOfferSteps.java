package com.steps_api;

import com.models.OfferObjectModel;

import java.net.http.HttpRequest;

public class PostOfferSteps extends BaseApiSteps {

    public void SendEditOfferRequest(OfferObjectModel newOfferData, String offerId){
        String offerDataJson = CovertObjectToJsonString(newOfferData);
        HttpRequest request = baseHttpClient.BuildPostRequest(offerDataJson, RequestUri(offerId));
        baseHttpClient.SendRequestWithStatusValidation(request);
    }
}
