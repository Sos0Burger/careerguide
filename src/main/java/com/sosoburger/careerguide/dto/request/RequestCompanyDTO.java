package com.sosoburger.careerguide.dto.request;

import com.sosoburger.careerguide.dao.CompanyDAO;
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
public class RequestCompanyDTO {

    private String companyName;
    private String address;
    private String description;
    private String phone;
    private String email;
    private String image;

    public CompanyDAO toDAO() throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CompanyDAO.class);
    }
}
