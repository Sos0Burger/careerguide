package com.sosoburger.careerguide.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSignUpDTO {
    private Integer SignUpId;

    private String name;

    private String phone;

    private Date date;

    private Boolean status;

    private String purpose;

    private String group;

    private String direction;

    private Integer count;

    private Integer scheduleId;

    private Integer institutionId;

    private Integer file;

    private String image;
}
