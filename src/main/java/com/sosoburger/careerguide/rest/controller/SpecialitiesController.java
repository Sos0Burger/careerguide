package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dto.response.ResponseSpecialityDTO;
import com.sosoburger.careerguide.rest.api.SpecialityApi;
import com.sosoburger.careerguide.service.speciality.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpecialitiesController implements SpecialityApi {

    @Autowired
    private final SpecialityService specialityService;

    public SpecialitiesController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public ResponseEntity<List<ResponseSpecialityDTO>> getSpecialities(String type) {
        List<ResponseSpecialityDTO> list = new ArrayList<>();
        specialityService.getSpecialities(type).forEach(item->list.add(item.toDTO()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
