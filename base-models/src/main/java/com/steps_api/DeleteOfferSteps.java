package com.steps_api;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteOfferSteps extends BaseApiSteps {

    public HttpResponse DeleteOfferById(String offerId){
        HttpRequest request = baseHttpClient.BuildDeleteRequest(RequestUri(offerId));
        HttpResponse response = baseHttpClient.SendRequestWithStatusValidation(request);
        return response;
    }
}