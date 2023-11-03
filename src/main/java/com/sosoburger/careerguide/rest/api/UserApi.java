package com.sosoburger.careerguide.rest.api;

import com.sosoburger.careerguide.dto.request.LoginDTO;
import com.sosoburger.careerguide.dto.request.RegDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserApi {
    @PostMapping("/signin")
    ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto);
    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@RequestBody RegDTO RegDTO);
}
