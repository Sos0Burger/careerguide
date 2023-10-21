package com.sosoburger.careerguide.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDAO {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "max_people")
    private Integer max;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private CompanyDAO company;

    @OneToMany(mappedBy = "schedule")
    private List<SignUpDAO> signUps;

}
