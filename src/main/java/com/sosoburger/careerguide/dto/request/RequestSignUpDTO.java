package com.sosoburger.careerguide.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sosoburger.careerguide.dao.SignUpDAO;
import com.sosoburger.careerguide.service.institution.InstitutionService;
import com.sosoburger.careerguide.service.schedule.ScheduleService;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignUpDTO {

    @JsonIgnore
    @Autowired
    private InstitutionService institutionService;
    @JsonIgnore
    @Autowired
    private ScheduleService scheduleService;
    private String name;

    private String phone;

    private Date date;

    private Integer schedule;

    private Integer institution;

    public SignUpDAO toDAO() throws ParseException {
        return new SignUpDAO(
                null,
                name,
                phone,
                date,
                scheduleService.get(schedule),
                institutionService.get(institution));
    }
}
