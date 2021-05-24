package com.intuit.craft.external.impl;

import com.intuit.craft.dto.ProductResponseDto;
import com.intuit.craft.external.ProductRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Service
public class ProductRestServiceImpl implements ProductRestService {

    RestServiceImpl restServiceImpl;

    @Autowired
    public ProductRestServiceImpl(RestServiceImpl restServiceImpl) {
        this.restServiceImpl = restServiceImpl;
    }

    @Override
    public ProductResponseDto callProductService(String url, Object request, String service) throws IOException {
        URI uri = URI.create(url);
        ProductResponseDto response = restServiceImpl.post(request, uri, service, ProductResponseDto.class);
        return response;
    }
}
