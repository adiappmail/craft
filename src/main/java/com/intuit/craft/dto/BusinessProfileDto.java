package com.intuit.craft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties
public class BusinessProfileDto {

    String companyName;
    String legalName;
    Address businessAddress;
    String legalAddress;
    TaxIdentifiers taxIdentifiers;
    String email;
    String website;


}
