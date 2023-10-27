package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.request.RequestSignUpDTO;
import com.sosoburger.careerguide.dto.response.ResponseSignUpDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("SignUp")
public interface SignUpApi {
    @PostMapping
    @Operation(description = "Создание заявки")
    ResponseEntity<ResponseSignUpDTO> createSignUp(@RequestBody RequestSignUpDTO request);

    @PutMapping("/{id}")
    @Operation(description = "Обновление заявки")
    ResponseEntity<ResponseSignUpDTO> updateSignUp(@PathVariable("id") Integer id, @RequestBody RequestSignUpDTO request);

    @GetMapping("/{id}")
    @Operation(description = "Получение заявки")
    ResponseEntity<ResponseSignUpDTO> getSignUp(@PathVariable("id") Integer id);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление заявки")
    ResponseEntity<?> deleteSignUp(@PathVariable("id") Integer id);
}
