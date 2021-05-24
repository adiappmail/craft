package com.intuit.craft.service.impl;

import com.intuit.craft.dto.Address;
import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.TaxIdentifiers;
import com.intuit.craft.service.ValidatorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public String validateBusinessProfile(BusinessProfileDto dto) {

        String response = " ";

        if (StringUtils.isBlank(dto.getCompanyName()))
            response = response.concat(" Company Name cannot be blank");
        if (StringUtils.isBlank(dto.getLegalName()))
            response = response.concat(" Legal Name cannot be blank");
        if (StringUtils.isBlank(dto.getEmail()))
            response = response.concat(" Email cannot be blank");
        if (StringUtils.isBlank(dto.getLegalAddress()))
            response = response.concat(" Legal address cannot be blank");
        if (StringUtils.isBlank(dto.getWebsite()))
            response = response.concat(" Website cannot be blank");
        if (Objects.isNull(dto.getBusinessAddress()))
            response = response.concat(" Add Business address");
        else {
            Address address = dto.getBusinessAddress();
            if (StringUtils.isBlank(address.getLine1()))
                response = response.concat(" Line1 cannot be blank");
            if (StringUtils.isBlank(address.getCity()))
                    response = response.concat(" City cannot be blank");
            if (StringUtils.isBlank(address.getState()))
                    response = response.concat(" State cannot be blank");
            if (StringUtils.isBlank(address.getZip()))
                    response = response.concat(" Zip cannot be blank");
            if (StringUtils.isBlank(address.getCountry()))
                    response = response.concat(" Country cannot be blank");
        }

        if (Objects.isNull(dto.getTaxIdentifiers()))
            response = response.concat(" Add Tax identifiers");
        else {
            TaxIdentifiers tax = dto.getTaxIdentifiers();
            if (StringUtils.isBlank(tax.getEin()) && StringUtils.isBlank(tax.getPan()))
                response = response.concat(" All tax identifiers cannot be blank");
        }

        return response;
    }

    @Override
    public String validateUpdate(BusinessProfileDto dto) {

        if(Objects.isNull(dto)) {
            return  "* Update object is required";
        }
        if(StringUtils.isBlank(dto.getEmail())) {
            return  " Cannot udpate without email";
        }
        return "";
    }
}
