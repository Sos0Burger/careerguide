package com.sosoburger.careerguide.dao;

import com.sosoburger.careerguide.dto.response.ResponseInstitutionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Entity
@Table(name = "institutions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDAO {
    @Transient
    private final ModelMapper modelMapper = new ModelMapper();
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "institution")
    private List<SignUpDAO> signUps;
    public ResponseInstitutionDTO toDTO(){
        return modelMapper.map(this, ResponseInstitutionDTO.class);
    }
}
