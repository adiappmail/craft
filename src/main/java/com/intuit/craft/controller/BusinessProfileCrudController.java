package com.intuit.craft.controller;


import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.ResponseData;
import com.intuit.craft.exceptions.CraftException;
import com.intuit.craft.facade.BusinessProfileFacade;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/***
 * Controller for business profile operations
 */

@RequestMapping("/v1/business/profile")
@RestController
@Api(value = "Craft Demo")
public class BusinessProfileCrudController {



  private BusinessProfileFacade businessProfileFacade;

  @Autowired
    public BusinessProfileCrudController(BusinessProfileFacade businessProfileFacade) {
        this.businessProfileFacade = businessProfileFacade;
    }

    @ApiModelProperty(value = "Add new Business Profile ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request!")})
    @PostMapping(value = "")
    public ResponseEntity<ResponseData>  save(@RequestBody BusinessProfileDto businessProfileDto) {
      try {
          ResponseData responseData = businessProfileFacade.addNewBusinessProfile(businessProfileDto);
          if (responseData.isSuccess()) {
              return new ResponseEntity<>(responseData, HttpStatus.OK);
          } else {
              return new ResponseEntity<>(responseData, HttpStatus.valueOf(responseData.getCode()));
          }
      } catch (CraftException e) {
          return new ResponseEntity<>(new ResponseData(false, e.getMessage(), null, 500), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

    @ApiModelProperty(value = "Get Business Profile ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 400, message = "Bad Request!")})
   @GetMapping(value = "")
    public ResponseEntity<ResponseData> getProfile(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String legalName,
            @RequestParam(required = false) String companyName) {

      try {
          return new ResponseEntity<>(
                  businessProfileFacade.getByProfileByParams(email, legalName, companyName)
                  , HttpStatus.OK);

      }catch ( Exception e) {
          return new ResponseEntity<>(new ResponseData(false, e.getMessage(), null, 500), HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

    @ApiModelProperty(value = "Update Business Profile ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 400, message = "Bad Request!")})
   @PostMapping(value = "update")
   public ResponseEntity<ResponseData>  update(@RequestBody BusinessProfileDto businessProfileDto) {
      try {
          return new ResponseEntity<>(
                businessProfileFacade.updateBusinessProfile(businessProfileDto), HttpStatus.OK);
      }catch (CraftException e) {
          return new ResponseEntity<>(new ResponseData(false, e.getMessage(), null, 500), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
}
