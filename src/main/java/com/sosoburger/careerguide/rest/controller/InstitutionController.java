package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;
import com.sosoburger.careerguide.dto.response.ResponseInstitutionDTO;
import com.sosoburger.careerguide.dto.response.ResponseSignUpDTO;
import com.sosoburger.careerguide.rest.api.InstitutionApi;
import com.sosoburger.careerguide.service.institution.InstitutionService;
import com.sosoburger.careerguide.service.signup.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InstitutionController implements InstitutionApi {
    @Autowired
    private final InstitutionService institutionService;

    @Autowired
    private final SignUpService signUpService;

    public InstitutionController(InstitutionService institutionService, SignUpService signUpService) {
        this.institutionService = institutionService;
        this.signUpService = signUpService;
    }

    @Override
    public ResponseEntity<ResponseInstitutionDTO> createInstitution(RequestInstitutionDTO request) {
        var institution = institutionService.save(request);
        return new ResponseEntity<>(institution.toDTO(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseInstitutionDTO> updateInstitution(Integer id, RequestInstitutionDTO request) {
        var institution = institutionService.update(id, request);
        return new ResponseEntity<>(institution.toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseInstitutionDTO> getInstitution(Integer id) {
        var institution = institutionService.get(id);
        return new ResponseEntity<>(institution.toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteInstitution(Integer id) {
        institutionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseSignUpDTO>> getPendingSignUps(Integer id) {
        List<ResponseSignUpDTO> list = new ArrayList<>();
        signUpService.getInstitutionPendingSignUps(id).forEach(item->list.add(item.toDTO()));
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseSignUpDTO>> getSignUpsArchive(Integer id) {
        List<ResponseSignUpDTO> list = new ArrayList<>();
        signUpService.getInstitutionSignUpsArchive(id).forEach(item->list.add(item.toDTO()));
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}