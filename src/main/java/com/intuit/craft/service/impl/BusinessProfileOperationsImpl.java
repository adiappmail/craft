package com.intuit.craft.service.impl;

import com.intuit.craft.dao.BusinessProfile;
import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.ResponseData;
import com.intuit.craft.exceptions.CraftException;
import com.intuit.craft.mapper.BusinessProfileDaoMapper;
import com.intuit.craft.repo.BusinessProfileRepository;
import com.intuit.craft.service.BusinessProfileOperations;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BusinessProfileOperationsImpl implements BusinessProfileOperations {

    BusinessProfileRepository businessProfileRepository;
    BusinessProfileDaoMapper mapper;
    ProductCheckingServiceImpl productCheckingService;

    @Autowired
    public BusinessProfileOperationsImpl(BusinessProfileRepository businessProfileRepository, BusinessProfileDaoMapper mapper, ProductCheckingServiceImpl productCheckingService) {
        this.businessProfileRepository = businessProfileRepository;
        this.mapper = mapper;
        this.productCheckingService = productCheckingService;
    }

    @Override
    public ResponseData<BusinessProfile> save(BusinessProfileDto newBusinessProfileDto) {

        try {
            Optional<BusinessProfile> businessProfileDB = businessProfileRepository.findById(newBusinessProfileDto.getEmail());

            if (businessProfileDB.isPresent()) {
                return new ResponseData(false, "Business Profile with same email is already present", null, HttpStatus.CONFLICT.value());
            }

            BusinessProfile newBusinessProfile = businessProfileRepository.save(mapper.get(newBusinessProfileDto));
            return new ResponseData<BusinessProfile>(true, "New Profile created", newBusinessProfile, HttpStatus.OK.value());

        } catch (Exception e) {
            throw new CraftException("Error while adding profile  data " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public ResponseData<BusinessProfile> update(BusinessProfileDto businessProfileDto) {

        try {
            Optional<BusinessProfile> businessProfileDB = businessProfileRepository.findById(businessProfileDto.getEmail());

            if (!businessProfileDB.isPresent()) {
                return new ResponseData(false, "Business Profile not  present", null, HttpStatus.NOT_FOUND.value());
            }

            BusinessProfile updatedDao = mapper.get(businessProfileDto, businessProfileDB.get());
            if (Objects.isNull(updatedDao)) {
                return new ResponseData(false, "Same data cannot update", null, HttpStatus.BAD_REQUEST.value());
            }

            String productCheckResponse =  productCheckingService.getValidationResponseForUpdate(updatedDao);
            if(StringUtils.isNotBlank(productCheckResponse)) {
                return new ResponseData(false, productCheckResponse, null, HttpStatus.BAD_REQUEST.value());
            }
            return new ResponseData<BusinessProfile>(true, "Updated Profile",
                    businessProfileRepository.save(updatedDao)
                    , HttpStatus.OK.value());

        } catch (Exception e) {
            throw new CraftException("Error while updating data " + e.getMessage(), e.getCause());
        }

    }

}
