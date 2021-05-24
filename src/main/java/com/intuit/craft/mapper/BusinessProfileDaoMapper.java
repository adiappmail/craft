package com.intuit.craft.mapper;

import com.intuit.craft.dao.BusinessProfile;
import com.intuit.craft.dto.Address;
import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.TaxIdentifiers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BusinessProfileDaoMapper {

    public BusinessProfile get(BusinessProfileDto dto) {

        BusinessProfile bp = new BusinessProfile();
        bp.setCompanyName(dto.getCompanyName());
        bp.setEmail(dto.getEmail());
        bp.setBusinessAddress(dto.getBusinessAddress());
        bp.setLegalAddress(dto.getLegalAddress());
        bp.setLegalName(dto.getLegalName());
        bp.setTaxIdentifiers(dto.getTaxIdentifiers());
        bp.setWebsite(dto.getWebsite());
        return bp;
    }

    public BusinessProfileDto get(BusinessProfile dao) {

        return new BusinessProfileDto(
                dao.getCompanyName(),
                dao.getLegalName(),
                dao.getBusinessAddress(),
                dao.getLegalAddress(),
                dao.getTaxIdentifiers(),
                dao.getEmail(),
                dao.getWebsite()
        );
    }

    public BusinessProfile get(BusinessProfileDto dto, BusinessProfile bp) {

        boolean diff = false;

        if (StringUtils.isNotBlank(dto.getCompanyName()) &&
                !bp.getCompanyName().equalsIgnoreCase(dto.getCompanyName())) {
            bp.setCompanyName(dto.getCompanyName());
            diff = true;
        }
        if (StringUtils.isNotBlank(dto.getLegalName()) &&
                !bp.getLegalName().equalsIgnoreCase(dto.getLegalName())) {
            bp.setLegalName(dto.getLegalName());
            diff = true;
        }

        if (StringUtils.isNotBlank(dto.getLegalAddress()) &&
                !bp.getLegalAddress().equalsIgnoreCase(dto.getLegalAddress())) {
            bp.setLegalAddress(dto.getLegalAddress());
            diff = true;
        }
        if (StringUtils.isNotBlank(dto.getWebsite()) &&
                !bp.getWebsite().equalsIgnoreCase(dto.getWebsite())) {
            bp.setWebsite(dto.getWebsite());
            diff = true;
        }
        if (Objects.nonNull(dto.getBusinessAddress()) &&
                 ! (dto.getBusinessAddress() ==  bp.getBusinessAddress())) {
            bp.setBusinessAddress(dto.getBusinessAddress());
            diff = true;
        }

        if (Objects.nonNull(dto.getTaxIdentifiers()) &&
                ! (dto.getTaxIdentifiers() ==  bp.getTaxIdentifiers())) {
            bp.setTaxIdentifiers(dto.getTaxIdentifiers());
            diff = true;
        }

        if(!diff) {
            return null;
        }
        return bp;
    }
}