package com.sosoburger.careerguide.rest.api;


import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/company")
public interface CompanyApi {

    @PostMapping
    @Operation(description = "Создание компании")
    ResponseEntity<ResponseCompanyDTO> createcompany(@RequestBody RequestCompanyDTO request);

}
