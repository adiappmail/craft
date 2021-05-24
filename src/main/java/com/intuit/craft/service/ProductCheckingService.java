package com.intuit.craft.service;

import com.intuit.craft.dao.BusinessProfile;

public interface ProductCheckingService {

     String getValidationResponseForUpdate(BusinessProfile businessProfile);
}
