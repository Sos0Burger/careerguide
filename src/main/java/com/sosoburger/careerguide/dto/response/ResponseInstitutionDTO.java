package com.sosoburger.careerguide.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInstitutionDTO {
    private Integer id;

    private String name;

    private String image;

    private String description;

    private String email;

    private String phone;

    private String site;
}
