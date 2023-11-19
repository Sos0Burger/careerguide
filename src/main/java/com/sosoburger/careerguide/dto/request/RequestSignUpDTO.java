package com.sosoburger.careerguide.dto.request;

import com.sosoburger.careerguide.dao.SignUpDAO;
import jakarta.persistence.Transient;
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
    @Transient
    private static ModelMapper modelMapper = new ModelMapper();
    private String name;

    private String phone;

    private Date date;

    private Integer schedule;

    private Integer institution;

    private String purpose;

    private String groupname;

    private String direction;

    private Integer count;

    private Integer file;

    public SignUpDAO toDAO() throws ParseException {
        modelMapper.typeMap(RequestSignUpDTO.class, SignUpDAO.class).addMappings(mapper ->{
            mapper.skip(SignUpDAO::setSignUpId);
            mapper.skip(SignUpDAO::setInstitution);
            mapper.skip(SignUpDAO::setStatus);
            mapper.skip(SignUpDAO::setFile);
        });
        return modelMapper.map(this, SignUpDAO.class);
    }
}
