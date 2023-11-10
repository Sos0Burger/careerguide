package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.request.RequestSignUpDTO;
import com.sosoburger.careerguide.dto.response.ResponseSignUpDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/signup")
public interface SignUpApi {
    @PreAuthorize("hasRole('INSTITUTION')")
    @PostMapping
    @Operation(summary = "Создание заявки")
    ResponseEntity<ResponseSignUpDTO> createSignUp(@RequestBody RequestSignUpDTO request);

    @PreAuthorize("hasRole('INSTITUTION')")
    @PutMapping("/{id}")
    @Operation(summary = "Обновление заявки")
    ResponseEntity<ResponseSignUpDTO> updateSignUp(@PathVariable("id") Integer id, @RequestBody RequestSignUpDTO request);

    @PreAuthorize("hasRole('INSTITUTION')")
    @GetMapping("/{id}")
    @Operation(summary = "Получение заявки")
    ResponseEntity<ResponseSignUpDTO> getSignUp(@PathVariable("id") Integer id);

    @PreAuthorize("hasRole('INSTITUTION')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление заявки")
    ResponseEntity<?> deleteSignUp(@PathVariable("id") Integer id);

    @PreAuthorize("hasRole('COMPANY')")
    @PutMapping("/{id}/status")
    @Operation(summary = "Изменение статуса заявки")
    ResponseEntity<?> changeSignUpStatus(@PathVariable("id") Integer id, @RequestParam Boolean status);
}
