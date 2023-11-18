package com.sosoburger.careerguide.dao;

import com.sosoburger.careerguide.dto.response.ResponseInstitutionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "institutions", uniqueConstraints=@UniqueConstraint(columnNames={"user_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDAO {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserDAO user;
    public ResponseInstitutionDTO toDTO(){
        return new ResponseInstitutionDTO(
                id,
                name,
                image
        );
    }
}
