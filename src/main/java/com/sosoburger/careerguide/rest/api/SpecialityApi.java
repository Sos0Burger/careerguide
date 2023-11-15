package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.response.ResponseSpecialityDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/speciality")
public interface SpecialityApi {
    @GetMapping
    @Operation(summary = "Полчение подходящих специальностей")
    ResponseEntity<List<ResponseSpecialityDTO>> getSpecialities(@RequestParam("type") String type);
}
