package com.sosoburger.careerguide;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sosoburger.careerguide.dto.request.LoginDTO;
import com.sosoburger.careerguide.dto.request.RegDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper ob = new ObjectMapper();


    public static void CreateUser(MockMvc mockMvc,String login, String password, String role){
        try {
            mockMvc.perform(
                    post("/user/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ob.writeValueAsString(new RegDTO(login, password, role)))
            );
            System.out.println("Пользователь " + login + " создан");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    @Test
    @Sql(value = "classpath:reset.sql", executionPhase = BEFORE_TEST_METHOD)
    void registerUser() {
        try {
            RegDTO user = new RegDTO("company", "company", "COMPANY");

            mockMvc.perform(
                    post("/user/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ob.writeValueAsString(user))
            ).andExpect(status().isCreated());

            LoginDTO login = new LoginDTO(user.getLogin(), user.getPassword());

            mockMvc.perform(
                    post("/user/signin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ob.writeValueAsString(login))
            ).andExpect(status().isOk());
        }
        catch (Exception ignored){
            assert(false);
        }
    }
}