package com.sosoburger.careerguide.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompanyDTO {
    private Integer companyId;
    private String companyName;
    private String address;
    private String description;
    private String phone;
    private String email;
    private String image;
}
