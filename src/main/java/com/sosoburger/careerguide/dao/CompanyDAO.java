package com.sosoburger.careerguide.dao;

import com.sosoburger.careerguide.dto.response.ResponseCompanyDTO;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import java.util.List;

@Entity
@Table(name = "companies", uniqueConstraints=@UniqueConstraint(columnNames={"user_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDAO {

    @Transient
    private final ModelMapper modelMapper = new ModelMapper();

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "company")
    private List<ScheduleDAO> schedule;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserDAO user;

    public ResponseCompanyDTO toDTO(){
        return modelMapper.map(this, ResponseCompanyDTO.class);
    }
}
