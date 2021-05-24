package com.intuit.craft.executor;

import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.ProductResponseDto;

import java.util.List;

public interface ProductsServiceCalls {

    List<ProductResponseDto> executeCallsToAllProducts(BusinessProfileDto businessProfile);
}
