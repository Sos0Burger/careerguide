package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dto.request.RequestSignUpDTO;
import com.sosoburger.careerguide.dto.response.ResponseSignUpDTO;
import com.sosoburger.careerguide.rest.api.SignUpApi;
import com.sosoburger.careerguide.service.signup.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SignUpController implements SignUpApi {

    @Autowired
    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @Override
    public ResponseEntity<ResponseSignUpDTO> createSignUp(RequestSignUpDTO request) {
        return new ResponseEntity<>(signUpService.save(request).toDTO(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseSignUpDTO> updateSignUp(Integer id, RequestSignUpDTO request) {
        return new ResponseEntity<>(signUpService.update(id, request).toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseSignUpDTO> getSignUp(Integer id) {
        return new ResponseEntity<>(signUpService.get(id).toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteSignUp(Integer id) {
        signUpService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
