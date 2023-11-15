package com.sosoburger.careerguide.dao;

import com.sosoburger.careerguide.dto.response.ResponseSpecialityDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Entity
@Table(name = "specialities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialityDAO {
    @Transient
    private final ModelMapper modelMapper = new ModelMapper();
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer signUpId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    public ResponseSpecialityDTO toDTO(){
        return modelMapper.map(this, ResponseSpecialityDTO.class);
    }
}
