package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.request.RequestInstitutionDTO;
import com.sosoburger.careerguide.dto.response.ResponseInstitutionDTO;
import com.sosoburger.careerguide.dto.response.ResponseSignUpDTO;
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
    @Operation(summary = "Получение всех учебных заведений")
    ResponseEntity<List<ResponseInstitutionDTO>> getAllInstitution();
  
    @GetMapping("/{id}/signup-status")
    @Operation(summary = "Заявки, которые ещё не одобрили/отклонили")
    ResponseEntity<List<ResponseSignUpDTO>> getPendingSignUps(@PathVariable("id") Integer id);

    @GetMapping("/{id}/signup-archive")
    @Operation(summary = "Заявки, которые уже были одобрены/отклонены")
    ResponseEntity<List<ResponseSignUpDTO>> getSignUpsArchive(@PathVariable("id") Integer id);

    @GetMapping("/login")
    @Operation(summary = "Получение школы по логину")
    ResponseEntity<ResponseInstitutionDTO> getInstitutionByLogin(@RequestParam("login")String login);
}
