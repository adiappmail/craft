package com.intuit.craft.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.intuit.craft.dto.Address;
import com.intuit.craft.dto.TaxIdentifiers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Document("business_profile")
public class BusinessProfile {

    String companyName;
    String legalName;
    Address businessAddress;
    String legalAddress;
    TaxIdentifiers taxIdentifiers;
    String website;
    @Id
    String email;
    @Version
    private Long version;
}

