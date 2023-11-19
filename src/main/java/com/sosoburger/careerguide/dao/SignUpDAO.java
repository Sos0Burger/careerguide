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
    private Integer signUpId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "signup_date")
    private Date date;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "groupname")
    private String groupname;

    @Column(name = "direction")
    private String direction;

    @Column(name = "count")
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleDAO schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", nullable = false)
    private InstitutionDAO institution;

    @OneToOne
    @JoinColumn(name = "file_id")
    private FileDAO file;

    public ResponseSignUpDTO toDTO() {
        return new ResponseSignUpDTO(
                signUpId,
                name,
                phone,
                date,
                status,
                purpose,
                groupname,
                direction,
                count,
                schedule.getId(),
                institution.getId(),
                file == null ?
                        null :
                        file.getId()
        );
    }
}
