package com.steps_api;

import com.base.api_client.BaseHttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.models.OfferObjectModel;
import org.apache.http.client.utils.URIBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Properties;

public abstract class BaseApiSteps {
    protected BaseHttpClient baseHttpClient = new BaseHttpClient();
    protected ObjectMapper objectMapper = new ObjectMapper();
    private FileInputStream _fileInputStream;
    private Properties _prop = new Properties();

    public BaseApiSteps(){
        try {
            String filePath = new File("../offer-service/config.properties").getAbsolutePath();
            _fileInputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        _prop = GetProperties(_fileInputStream);
    }

    public URI RequestUri(){
        try {
            return RequestUriBuilder().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public URI RequestUri(String offerId) {
        try {
            return RequestUriBuilder().setPathSegments(
                    _prop.getProperty("apiUrlPathSegment1"),
                    _prop.getProperty("apiUrlPathSegment2"),
                    offerId).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<OfferObjectModel> ResponseToOfferObjectList(HttpResponse jsonList){
        try {
            return objectMapper.readValue(jsonList.body().toString(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected OfferObjectModel ResponseToOfferObject(HttpResponse response){
        try {
            return objectMapper.readValue(response.body().toString(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected String CovertObjectToJsonString(OfferObjectModel offer){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(offer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private URIBuilder RequestUriBuilder(){
        URIBuilder builder = new URIBuilder()
                    .setScheme(_prop.getProperty("apiUrlScheme"))
                    .setHost(_prop.getProperty("apiUrlHost"))
                    .setPort(Integer.parseInt(_prop.getProperty("apiUrlPort")))
                    .setPathSegments(
                            _prop.getProperty("apiUrlPathSegment1"),
                            _prop.getProperty("apiUrlPathSegment2"));
        return builder;
    }

    private Properties GetProperties(FileInputStream fileInputStream){
        try {
            _prop.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return _prop;
    }
}