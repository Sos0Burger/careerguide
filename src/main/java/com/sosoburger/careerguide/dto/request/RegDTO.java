package com.sosoburger.careerguide.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegDTO {
    private String login;
    private String password;
    private String role;
}
