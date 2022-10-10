package com.steps_api;

import com.models.OfferObjectModel;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetOfferSteps extends BaseApiSteps {

    public HttpResponse SendGetOfferListRequest(){
        HttpRequest request = baseHttpClient.BuildGetRequest(RequestUri());
        return baseHttpClient.SendRequestWithStatusValidation(request);
    }

    public OfferObjectModel GetParticularOfferById(String offerId){
        HttpRequest request = baseHttpClient.BuildGetRequest(RequestUri(offerId));
        HttpResponse response = baseHttpClient.SendRequestWithStatusValidation(request);
        return ResponseToOfferObject(response);
    }

    public HttpResponse GetParticularOfferResponseById(String offerId){
        HttpRequest request = baseHttpClient.BuildGetRequest(RequestUri(offerId));
        HttpResponse response = baseHttpClient.SendRequestWithoutValidation(request);
        return response;
    }

    public OfferObjectModel GetOfferByName(String offerName){
        HttpResponse response = SendGetOfferListRequest();
        String offerId = GetOfferIdFromListByName(response, offerName);
        return GetParticularOfferById(offerId);
    }

    public String GetOfferIdFromListByName(HttpResponse responseOfferList, String offerName) {
        List<OfferObjectModel> offersList = ResponseToOfferObjectList(responseOfferList);
        return GetOfferFromList(offersList, offerName).id;
    }

    public boolean CheckIfOfferIsInList(HttpResponse response, String offerName){
        List<OfferObjectModel> offersList = ResponseToOfferObjectList(response);
        for(var offerFromList : offersList){
            if(offerFromList.name.equals(offerName)){
                System.out.println("Offer is exist id:'"+offerFromList.id+"', Name:'"+offerFromList.name+"'.");
                return true;
            }
        }
        System.out.println("Offer with name '"+offerName+"' is not found.");
        return false;
    }

    public OfferObjectModel GetOfferFromListByName(HttpResponse responseOfferList, String offerName) {
        List<OfferObjectModel> offersList = ResponseToOfferObjectList(responseOfferList);
        return GetOfferFromList(offersList, offerName);
    }

    private OfferObjectModel GetOfferFromList(List<OfferObjectModel> offersList, String offerName){
        for(var offerFromList : offersList){
            if(offerFromList.name.equals(offerName)){
                System.out.println("Found offer id:'"+offerFromList.id+"', Name:'"+offerFromList.name+"'.");
                return offerFromList;
            }
        }
        throw new RuntimeException("Offer with name '"+offerName+"' is not found.");
    }
}