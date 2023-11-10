package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dao.CompanyDAO;
import com.sosoburger.careerguide.dao.InstitutionDAO;
import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;
import com.sosoburger.careerguide.dto.response.ResponseInstitutionDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/institution")
@PreAuthorize("hasRole('INSTITUTION')")
public interface InstitutionApi {
    @PostMapping
    @Operation(summary = "Создание учебного заведения")
    ResponseEntity<ResponseInstitutionDTO> createInstitution(@RequestBody RequestInstitutionDTO request);

    @PutMapping("/{id}")
    @Operation(summary = "Обновление учебного заведения")
    ResponseEntity<ResponseInstitutionDTO> updateInstitution(@PathVariable("id") Integer id, @RequestBody RequestInstitutionDTO request);

    @GetMapping("/{id}")
    @Operation(summary = "Получение учебного заведения")
    ResponseEntity<ResponseInstitutionDTO> getInstitution(@PathVariable("id") Integer id);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление учебного заведения")
    ResponseEntity<?> deleteInstitution(@PathVariable("id") Integer id);
    @GetMapping()
    @Operation(summary = "Получить всех учебных заведений")
    ResponseEntity<List<InstitutionDAO>> getAllInstitution();
}
