package com.intuit.craft.service.impl;

import com.intuit.craft.config.ProductsRegistry;
import com.intuit.craft.dao.BusinessProfile;
import com.intuit.craft.dto.ProductResponseDto;
import com.intuit.craft.executor.impl.ProductsServiceCallsImpl;
import com.intuit.craft.mapper.BusinessProfileDaoMapper;
import com.intuit.craft.service.ProductCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCheckingServiceImpl implements ProductCheckingService {

    @Autowired
    ProductsServiceCallsImpl productsServiceCallsImpl;
    @Autowired
    BusinessProfileDaoMapper mapper;
    @Autowired
    ProductsRegistry productsRegistry;

    @Override
    public String getValidationResponseForUpdate(BusinessProfile businessProfile) {

        List<ProductResponseDto> responsesFromOtherPS = productsServiceCallsImpl.executeCallsToAllProducts(mapper.get(businessProfile));
        String response = "";
        if(responsesFromOtherPS.size() == productsRegistry.getMap().size()) {
          for (ProductResponseDto p : responsesFromOtherPS) {
              if(!p.isStatus()) {
                  response.concat(p.getProductName()).concat( " Failed the update due to reason ").concat(p.getRejectMessage()).concat("***\\n***");
              }
          }
        }
        return response;
    }
}
