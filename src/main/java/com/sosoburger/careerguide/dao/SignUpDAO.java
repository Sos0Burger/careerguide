package com.sosoburger.careerguide.dao;

import com.sosoburger.careerguide.dto.response.ResponseSignUpDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Entity
@Table(name = "signups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDAO {
    @Transient
    private final ModelMapper modelMapper = new ModelMapper();

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "signup_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="schedule_id", nullable=false)
    private ScheduleDAO schedule;

    @ManyToOne
    @JoinColumn(name="institution_id", nullable=false)
    private InstitutionDAO institution;

    public ResponseSignUpDTO toDTO(){
        return modelMapper.map(this, ResponseSignUpDTO.class);
    }
}
