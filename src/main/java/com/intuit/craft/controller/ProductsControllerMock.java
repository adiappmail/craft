package com.intuit.craft.controller;


import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.ProductResponseDto;
import com.intuit.craft.dto.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/products/")
@RestController
public class ProductsControllerMock {


    @PostMapping(value = "product1/mock")
    public ResponseEntity<ResponseData> product1(@RequestBody BusinessProfileDto businessProfileDto) {
        return new ResponseEntity<>(new ResponseData(true, "Success", new ProductResponseDto("product1", true, ""), 200), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping(value = "product2/mock")
    public ResponseEntity<ResponseData> product2(@RequestBody BusinessProfileDto businessProfileDto) {
        return new ResponseEntity<>(new ResponseData(true, "Success", new ProductResponseDto("product2", true, ""), 200), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping(value = "product3/mock")
    public ResponseEntity<ResponseData> product3(@RequestBody BusinessProfileDto businessProfileDto) {
        return new ResponseEntity<>(new ResponseData(true, "Success", new ProductResponseDto("product3", true, ""), 200), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
