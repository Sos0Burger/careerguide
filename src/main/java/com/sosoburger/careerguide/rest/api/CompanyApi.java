package com.sosoburger.careerguide.rest.api;


import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/company")
public interface CompanyApi {

    @PostMapping
    @Operation(description = "Создание компании")
    ResponseEntity<ResponseCompanyDTO> createCompany(@RequestBody RequestCompanyDTO request);

    @PutMapping("/{id}")
    @Operation(description = "Обновление компании")
    ResponseEntity<ResponseCompanyDTO> updateCompany(@PathVariable("id") Integer id, @RequestBody RequestCompanyDTO request);

    @GetMapping("/{id}")
    @Operation(description = "Получение компании")
    ResponseEntity<ResponseCompanyDTO> getCompany(@PathVariable("id") Integer id);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление компании")
    ResponseEntity<?> delete(@PathVariable("id") Integer id);
}
