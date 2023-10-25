package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import com.sosoburger.careerguide.rest.api.CompanyApi;
import com.sosoburger.careerguide.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController implements CompanyApi {
    @Autowired
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }
    @Override
    public ResponseEntity<ResponseCompanyDTO> createcompany(RequestCompanyDTO request) {
        var company = companyService.save(request);
        return new ResponseEntity<>(company.toDTO(), HttpStatus.CREATED);
    }
}
