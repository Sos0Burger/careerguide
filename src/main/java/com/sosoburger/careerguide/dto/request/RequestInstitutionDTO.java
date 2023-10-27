package com.sosoburger.careerguide.dto.request;

import com.sosoburger.careerguide.dao.InstitutionDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.text.ParseException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestInstitutionDTO {

    private Integer id;

    private String name;

    private String image;
    public InstitutionDAO toDAO() throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, InstitutionDAO.class);
    }
}
