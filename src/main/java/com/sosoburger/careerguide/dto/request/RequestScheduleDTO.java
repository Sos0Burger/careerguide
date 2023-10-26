package com.sosoburger.careerguide.dto.request;

import com.sosoburger.careerguide.dao.InstitutionDAO;
import com.sosoburger.careerguide.dao.ScheduleDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestScheduleDTO {
    private Date date;
    private Integer max;
    private Integer companyId;
    public ScheduleDAO toDAO() throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ScheduleDAO.class);
    }
}
