package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.request.LoginDTO;
import com.sosoburger.careerguide.dto.request.RegDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserApi {
    @PostMapping("/signin")
    @Operation(summary = "Авторизация")
    ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto);
    @PostMapping("/signup")

    @Operation(summary = "Создание пользователя")
    ResponseEntity<?> registerUser(@RequestBody RegDTO RegDTO);
}
