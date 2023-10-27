package com.sosoburger.careerguide.dto.request;

import com.sosoburger.careerguide.dao.SignUpDAO;
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
public class RequestSignUpDTO {

    private String name;

    private String phone;

    private Date date;

    public SignUpDAO toDAO() throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, SignUpDAO.class);
    }
}
