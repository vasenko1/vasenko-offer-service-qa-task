package com.base.api_client;

import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class BaseHttpClient {
    public HttpRequest BuildGetRequest(URI requestUri) {
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .GET()
                .uri(requestUri)
                .build();
        System.out.println(request.method()+" "+request.uri());
        return request;
    }

    public HttpRequest BuildDeleteRequest(URI requestUri){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .DELETE()
                .uri(requestUri)
                .build();
        System.out.println(request.method()+" "+request.uri());
        return request;
    }

    public HttpRequest BuildPutRequest(String requestBody, URI requestUrl){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(requestUrl)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
        System.out.println(request.method() + " " + request.uri());
        return request;
    }

    public HttpRequest BuildPostRequest(String requestBody, URI requestUrl){
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(requestUrl)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
        System.out.println(request.method() + " " + request.uri());
        return request;
    }

    public HttpResponse SendRequestWithStatusValidation(HttpRequest request){
        HttpResponse response = SendRequest(request);
        CheckIfResponseOk(response);
        return response;
    }

    public HttpResponse SendRequestWithoutValidation(HttpRequest request){
        HttpResponse response = SendRequest(request);
        System.out.println("  Response Status Code: "+response.statusCode());
        System.out.println("  Response Body: "+ response.body().toString());
        return response;
    }

    private HttpResponse SendRequest(HttpRequest request){
        HttpResponse response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private void CheckIfResponseOk(HttpResponse response){
        int code = Integer.parseInt(""+response.statusCode());
        if(code >= 200 && code <= 299){
            System.out.println("  Response Status Code: "+response.statusCode());
            System.out.println("  Response Body: "+ response.body().toString());
        }
        else {
            System.out.println("Response Status Code: "+response.statusCode());
            System.out.println("Response Body: "+ response.body().toString());
            throw new RuntimeException("Request error! Response Status Code: "+response.statusCode());
        }
    }
}