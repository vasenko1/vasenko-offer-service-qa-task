package com.steps_api;

import com.models.OfferObjectModel;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PutOfferSteps extends BaseApiSteps {

    public HttpResponse CreateOffer(OfferObjectModel offer){
        String json = CovertObjectToJsonString(offer);
        HttpRequest request = baseHttpClient.BuildPutRequest(json, RequestUri());
        return baseHttpClient.SendRequestWithoutValidation(request); //
    }
}
