package com.intuit.craft.service;

import com.intuit.craft.dto.BusinessProfileDto;

public interface ValidatorService {

    String validateBusinessProfile(BusinessProfileDto dto);
    String validateUpdate(BusinessProfileDto dto);
}
