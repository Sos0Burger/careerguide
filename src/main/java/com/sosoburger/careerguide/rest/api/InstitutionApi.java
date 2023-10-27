package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;
import com.sosoburger.careerguide.dto.response.ResponseInstitutionDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/institution")
public interface InstitutionApi {
    @PostMapping
    @Operation(description = "Создание учебного заведения")
    ResponseEntity<ResponseInstitutionDTO> createInstitution(@RequestBody RequestInstitutionDTO request);

    @PutMapping("/{id}")
    @Operation(description = "Обновление учебного заведения")
    ResponseEntity<ResponseInstitutionDTO> updateInstitution(@PathVariable("id") Integer id, @RequestBody RequestInstitutionDTO request);

    @GetMapping("/{id}")
    @Operation(description = "Получение учебного заведения")
    ResponseEntity<ResponseInstitutionDTO> getInstitution(@PathVariable("id") Integer id);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление учебного заведения")
    ResponseEntity<?> deleteInstitution(@PathVariable("id") Integer id);
}
