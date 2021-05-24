package com.intuit.craft.repo;

import com.intuit.craft.dao.BusinessProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessProfileRepository extends MongoRepository<BusinessProfile, String> {

    List<BusinessProfile> findByEmailOrLegalNameOrCompanyName(String email, String legalName, String companyName);
}
