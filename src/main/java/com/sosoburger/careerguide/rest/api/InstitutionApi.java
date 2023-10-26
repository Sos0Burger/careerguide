package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dao.InstitutionDAO;
import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;
import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseInstitutionDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/institution")
public interface InstitutionApi {
    @PostMapping
    @Operation(description = "Создание института")
    ResponseEntity<ResponseInstitutionDTO> createinstitution(@RequestBody RequestInstitutionDTO request);

    @PutMapping("/{id}")
    @Operation(description = "Обновление института")
    ResponseEntity<ResponseInstitutionDTO> updateinstitution(@PathVariable("id") Integer id, @RequestBody RequestInstitutionDTO request);

    @GetMapping("/{id}")
    @Operation(description = "Получение института")
    ResponseEntity<ResponseInstitutionDTO> getinstitution(@PathVariable("id") Integer id);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление института")
    ResponseEntity<?> deleteinstitution(@PathVariable("id") Integer id);
}
