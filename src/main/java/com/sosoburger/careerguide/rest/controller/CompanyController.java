package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseScheduleDTO;
import com.sosoburger.careerguide.rest.api.CompanyApi;
import com.sosoburger.careerguide.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyController implements CompanyApi {
    @Autowired
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<ResponseCompanyDTO> createCompany(RequestCompanyDTO request) {
        var company = companyService.save(request);
        return new ResponseEntity<>(company.toDTO(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseCompanyDTO> updateCompany(Integer id, RequestCompanyDTO request) {
        var company = companyService.update(id, request);
        return new ResponseEntity<>(company.toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseCompanyDTO> getCompany(Integer id) {
        var company = companyService.get(id);
        return new ResponseEntity<>(company.toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        companyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseScheduleDTO>> getSchedule(Integer id) {
        var list = companyService.getSchedule(id);
        List<ResponseScheduleDTO> schedule = new ArrayList<>();
        list.forEach(scheduleDAO -> schedule.add(scheduleDAO.toDTO()));
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }
}
