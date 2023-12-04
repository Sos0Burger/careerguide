package com.sosoburger.careerguide;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sosoburger.careerguide.dto.request.RequestCompanyDTO;
import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CompanyControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper ob = new ObjectMapper();

    @Test
    @SqlGroup({
            @Sql(value = "classpath:reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    @WithMockUser(username = "company", password = "company", roles = "COMPANY")
    void createCompany() {
        UserControllerTest.CreateUser(mockMvc, "company", "company", "COMPANY");
        try {
            RequestCompanyDTO companyDTO = new RequestCompanyDTO(
                    "Компания",
                    "г. Пенза, ул. Гачи 1337",
                    "Очень хорошее предприятие",
                    "894343534",
                    "company@gmai;.cum",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1025px-Cat03.jpg");

            mockMvc.perform(
                            post("/company")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(ob.writeValueAsBytes(companyDTO))
                                    .param("login", "company")
                    ).andExpect(status().isCreated())
                    .andDo(result -> {
                        var response = ob.readValue(result.getResponse().getContentAsByteArray(), ResponseCompanyDTO.class);
                        assert (response.getCompanyId() == 1);
                        assert (response.getCompanyName().equals(companyDTO.getCompanyName()));
                        assert (response.getImage().equals(companyDTO.getImage()));
                        assert (response.getEmail().equals(companyDTO.getEmail()));
                        assert (response.getAddress().equals(companyDTO.getAddress()));
                        assert (response.getDescription().equals(companyDTO.getDescription()));
                        assert (response.getPhone().equals(companyDTO.getPhone()));
                    })
            ;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void updateCompany() {
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void getCompany() {
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void delete() {
    }
}