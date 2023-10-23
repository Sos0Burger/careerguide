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
public class RequestScheduleDTO {
    private Date date;
    private Integer max;
    private Integer companyId;
}
