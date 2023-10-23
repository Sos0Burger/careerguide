package com.sosoburger.careerguide.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignUpDTO {

    private String name;

    private String phone;

    private Date date;
}
