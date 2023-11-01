package com.sosoburger.careerguide.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "codes")
public class EmailCodeDAO {
    @Id
    private String email;

    @Column(name = "code")
    private Integer code;
}
