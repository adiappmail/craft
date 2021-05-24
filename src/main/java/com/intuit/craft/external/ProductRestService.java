package com.intuit.craft.external;

import com.intuit.craft.dto.ProductResponseDto;
import com.intuit.craft.dto.ResponseData;

import java.io.IOException;

public interface ProductRestService {

    ProductResponseDto callProductService(String url, Object request, String service) throws IOException;
}
