package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dto.request.RequestScheduleDTO;
import com.sosoburger.careerguide.dto.response.ResponseScheduleDTO;
import com.sosoburger.careerguide.rest.api.ScheduleApi;
import com.sosoburger.careerguide.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ScheduleController implements ScheduleApi {
    @Autowired
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public ResponseEntity<ResponseScheduleDTO> createInstitution(RequestScheduleDTO request) {
        var schedule = scheduleService.save(request);
        return new ResponseEntity<>(schedule.toDTO(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseScheduleDTO> updateInstitution(Integer id, RequestScheduleDTO request) {
        var schedule = scheduleService.update(id, request);
        return new ResponseEntity<>(schedule.toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseScheduleDTO> getInstitution(Integer id) {
        var schedule = scheduleService.get(id);
        return new ResponseEntity<>(schedule.toDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteInstitution(Integer id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
