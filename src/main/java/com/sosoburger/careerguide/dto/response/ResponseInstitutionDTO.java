package com.sosoburger.careerguide.dto.response;

import com.sosoburger.careerguide.dao.SignUpDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInstitutionDTO {
    private Integer id;

    private String name;

    private String image;


}
