package com.sosoburger.careerguide.dto.request;

import lombok.Data;

@Data
public class RegDTO {
    private String email;
    private String password;
    private Integer code;
}
