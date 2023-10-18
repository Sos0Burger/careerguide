package com.sosoburger.careerguide.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDAO {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @Column(name = "company_name")
    private String companyName;
}
