package com.sosoburger.careerguide.dto.request;

import lombok.Data;

@Data
public class RegDTO {
    private String login;
    private String password;
    private String role;
}
