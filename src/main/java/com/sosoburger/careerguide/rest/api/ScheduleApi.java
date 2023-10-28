package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.request.RequestScheduleDTO;
import com.sosoburger.careerguide.dto.response.ResponseScheduleDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/schedule")
public interface ScheduleApi {
    @PostMapping
    @Operation(description = "Создание расписание")
    ResponseEntity<ResponseScheduleDTO> createInstitution(@RequestBody RequestScheduleDTO request);

    @PutMapping("/{id}")
    @Operation(description = "Обновление расписания")
    ResponseEntity<ResponseScheduleDTO> updateInstitution(@PathVariable("id") Integer id, @RequestBody RequestScheduleDTO request);

    @GetMapping("/{id}")
    @Operation(description = "Получение расписания")
    ResponseEntity<ResponseScheduleDTO> getInstitution(@PathVariable("id") Integer id);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление расписания")
    ResponseEntity<?> deleteInstitution(@PathVariable("id") Integer id);

}
