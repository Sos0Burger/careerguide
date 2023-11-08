package com.sosoburger.careerguide.rest.api;


import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseScheduleDTO;
import com.sosoburger.careerguide.dto.response.ResponseSignUpDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/company")
public interface CompanyApi {

    @PostMapping
    @Operation(summary = "Создание компании")
    @PreAuthorize("hasRole('COMPANY')")
    ResponseEntity<ResponseCompanyDTO> createCompany(@RequestBody RequestCompanyDTO request);

    @PreAuthorize("hasRole('COMPANY')")
    @PutMapping("/{id}")
    @Operation(summary = "Обновление компании")
    ResponseEntity<ResponseCompanyDTO> updateCompany(@PathVariable("id") Integer id, @RequestBody RequestCompanyDTO request);

    @GetMapping("/{id}")
    @Operation(summary = "Получение компании")
    ResponseEntity<ResponseCompanyDTO> getCompany(@PathVariable("id") Integer id);
    @PreAuthorize("hasRole('COMPANY')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление компании")
    ResponseEntity<?> delete(@PathVariable("id") Integer id);

    @GetMapping("/{id}/schedule")
    @Operation(summary = "Расписание комании")
    ResponseEntity<List<ResponseScheduleDTO>> getSchedule(@PathVariable("id") Integer id);

    @GetMapping("/{id}/signup-status")
    @Operation(summary = "Заявки компании, которые ещё не одобрили/отклонили")
    ResponseEntity<List<ResponseSignUpDTO>> getPendingSignUps(@PathVariable("id") Integer id);

    @GetMapping("/{id}/signup-archive")
    @Operation(summary = "Заявки компании, которые уже были одобрены/отклонены")
    ResponseEntity<List<ResponseSignUpDTO>> getSignUpsArchive(@PathVariable("id") Integer id);
}
