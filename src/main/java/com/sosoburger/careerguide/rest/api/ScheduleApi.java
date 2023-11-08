package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.request.RequestScheduleDTO;
import com.sosoburger.careerguide.dto.response.ResponseScheduleDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/schedule")
@PreAuthorize("hasRole('COMPANY')")
public interface ScheduleApi {
    @PostMapping
    @Operation(summary = "Создание расписание")
    ResponseEntity<ResponseScheduleDTO> createSchedule(@RequestBody RequestScheduleDTO request);

    @PutMapping("/{id}")
    @Operation(summary = "Обновление расписания")
    ResponseEntity<ResponseScheduleDTO> updateSchedule(@PathVariable("id") Integer id, @RequestBody RequestScheduleDTO request);

    @GetMapping("/{id}")
    @Operation(summary = "Получение расписания")
    ResponseEntity<ResponseScheduleDTO> getSchedule(@PathVariable("id") Integer id);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление расписания")
    ResponseEntity<?> deleteSchedule(@PathVariable("id") Integer id);

}
