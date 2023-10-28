package com.sosoburger.careerguide.dao;


import com.sosoburger.careerguide.dto.response.ResponseScheduleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDAO {

    @Transient
    private final ModelMapper modelMapper = new ModelMapper();

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private CompanyDAO company;

    @OneToMany(mappedBy = "schedule")
    private List<SignUpDAO> signUps;

    public ResponseScheduleDTO toDTO(){
        return modelMapper.map(this, ResponseScheduleDTO.class);
    }

}
