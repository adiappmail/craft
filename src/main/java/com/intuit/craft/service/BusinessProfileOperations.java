package com.intuit.craft.service;

import com.intuit.craft.dao.BusinessProfile;
import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.ResponseData;

public interface BusinessProfileOperations {

    ResponseData<BusinessProfile> save(BusinessProfileDto newBusinessProfileDto);
    ResponseData<BusinessProfile> update(BusinessProfileDto businessProfileDto);
}
