package com.intuit.craft.external.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.craft.dto.ResponseData;
import com.intuit.craft.exceptions.CraftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@Component
public class RestServiceImpl {


    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    @Autowired
    public RestServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> T post(Object payLoad, URI uri, String service, Class<T> tClass) throws IOException {
        ResponseData responseData;
        try {
            responseData = restTemplate.postForObject(uri, payLoad, ResponseData.class);
        } catch (HttpClientErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            responseData = objectMapper.readValue(responseBodyAsString, ResponseData.class);
        }
        return getResponseData(service, responseData, tClass);

    }

    private <T> T getResponseData(String service, ResponseData responseData, Class<T> tClass) {
        if (!responseData.isSuccess()) {
            throw new CraftException(
                    String.format("message from  : %s %s", service, responseData.getErrorMessage()));
        }
        if (Objects.isNull(responseData.getResult())) return null;
        return objectMapper.convertValue(responseData.getResult(), tClass);
    }

}