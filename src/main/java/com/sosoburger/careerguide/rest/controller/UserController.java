package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dao.RoleDAO;
import com.sosoburger.careerguide.dao.UserDAO;
import com.sosoburger.careerguide.dto.request.LoginDTO;
import com.sosoburger.careerguide.dto.request.RegDTO;
import com.sosoburger.careerguide.repository.RoleRepository;
import com.sosoburger.careerguide.repository.UserRepository;
import com.sosoburger.careerguide.rest.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class UserController implements UserApi {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> authenticateUser(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registerUser(RegDTO RegDTO) {
        // add check for email exists in DB
        if(userRepository.existsByEmail(RegDTO.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        UserDAO user = new UserDAO();
        user.setEmail(RegDTO.getEmail());
        user.setPassword(passwordEncoder.encode(RegDTO.getPassword()));

        RoleDAO roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
