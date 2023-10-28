package com.sosoburger.careerguide.dto.response;


import com.sosoburger.careerguide.dao.ScheduleDAO;
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

    private Integer scheduleId;

    private Integer institutionId;
}
