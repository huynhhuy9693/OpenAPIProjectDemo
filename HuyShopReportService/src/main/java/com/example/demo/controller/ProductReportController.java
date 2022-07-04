package com.example.demo.controller;


import com.example.demo.model.ProductReport;
import com.example.demo.service.ProductReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/report")
public class ProductReportController {

    @Autowired
    ProductReportService service;
    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductReport>> getBookDataToReport()
    {
        return new ResponseEntity<>(service.getProductData(), HttpStatus.OK);
    }
}
