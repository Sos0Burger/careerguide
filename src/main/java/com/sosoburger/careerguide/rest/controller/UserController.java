package com.sosoburger.careerguide.rest.controller;

import com.sosoburger.careerguide.dao.RoleDAO;
import com.sosoburger.careerguide.dao.UserDAO;
import com.sosoburger.careerguide.dto.request.LoginDTO;
import com.sosoburger.careerguide.dto.request.RegDTO;
import com.sosoburger.careerguide.dto.response.ResponseUserDTO;
import com.sosoburger.careerguide.exception.NotFoundException;
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
    public ResponseEntity<ResponseUserDTO> authenticateUser(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getLogin(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(new ResponseUserDTO(userRepository.findByLogin(loginDto.getLogin()).get().getId()), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> registerUser(RegDTO regDTO) {
        // add check for email exists in DB
        if(userRepository.existsByLogin(regDTO.getLogin())){
            return new ResponseEntity<>("Логин уже занят!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        UserDAO user = new UserDAO();
        user.setLogin(regDTO.getLogin());
        user.setPassword(passwordEncoder.encode(regDTO.getPassword()));

        if(roleRepository.findByName("ROLE_"+regDTO.getRole()).isEmpty()){
            throw new NotFoundException("Такой роли не существует");
        };
        RoleDAO roles = roleRepository.findByName("ROLE_"+regDTO.getRole()).get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("Пользователь успешно зарегестрирован", HttpStatus.OK);
    }
}
