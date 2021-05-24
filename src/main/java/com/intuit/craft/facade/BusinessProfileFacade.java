package com.intuit.craft.facade;

import com.intuit.craft.dao.BusinessProfile;
import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.ResponseData;
import com.intuit.craft.exceptions.CraftException;
import com.intuit.craft.repo.BusinessProfileRepository;
import com.intuit.craft.service.impl.BusinessProfileOperationsImpl;
import com.intuit.craft.service.impl.ProductCheckingServiceImpl;
import com.intuit.craft.service.impl.ValidatorServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessProfileFacade {

    BusinessProfileOperationsImpl businessProfileOperations;
    ValidatorServiceImpl validatorService;
    BusinessProfileRepository repository;

    @Autowired
    public BusinessProfileFacade(BusinessProfileOperationsImpl businessProfileOperations, ValidatorServiceImpl validatorService, BusinessProfileRepository repository) {
        this.businessProfileOperations = businessProfileOperations;
        this.validatorService = validatorService;
        this.repository = repository;
    }

    public ResponseData addNewBusinessProfile(BusinessProfileDto businessProfileDto) throws CraftException {

        ResponseData responseData = new ResponseData();
        String validations = validatorService.validateBusinessProfile(businessProfileDto);
        if(StringUtils.isNotBlank(validations)) {
            return new ResponseData(false, validations, null, HttpStatus.BAD_REQUEST.value());
        }
        return businessProfileOperations.save(businessProfileDto);
    }

    public ResponseData<List<BusinessProfile>> getByProfileByParams(String email, String legalName, String companyName) {

        List<BusinessProfile> profileList =repository.findByEmailOrLegalNameOrCompanyName(email, legalName, companyName);
        if(profileList.size() <= 0)
            return  new ResponseData<>(true, "Not found", null, HttpStatus.NOT_FOUND.value());
        return new ResponseData(true, "", profileList, 200);
    }

    public ResponseData updateBusinessProfile(BusinessProfileDto businessProfileDto) throws CraftException {

        String validations = validatorService.validateUpdate(businessProfileDto);
        if(StringUtils.isNotBlank(validations)) {
            return new ResponseData(false, validations, null, HttpStatus.BAD_REQUEST.value());
        }

        return businessProfileOperations.update(businessProfileDto);
    }


}
