package com.sosoburger.careerguide.dto.request;

import jakarta.persistence.Column;

import java.util.Date;

public class RequestScheduleDTO {
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "max_people")
    private Integer max;
    private Integer companyId;
}
