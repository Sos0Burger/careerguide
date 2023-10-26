package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;
import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseInstitutionDTO;
import com.sosoburger.careerguide.rest.api.InstitutionApi;
import com.sosoburger.careerguide.service.institution.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstitutionController implements InstitutionApi {
    @Autowired
    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @Override
    public ResponseEntity<ResponseInstitutionDTO> createinstitution(RequestInstitutionDTO request) {
        var institution = institutionService.save(request);
        return new ResponseEntity<>(institution.toDTO(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseInstitutionDTO> updateinstitution(Integer id, RequestInstitutionDTO request) {
        var institution = institutionService.update(id, request);
        return new ResponseEntity<>(institution.toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseInstitutionDTO> getinstitution(Integer id) {
        var institution = institutionService.get(id);
        return new ResponseEntity<>(institution.toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteinstitution(Integer id) {
        institutionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}