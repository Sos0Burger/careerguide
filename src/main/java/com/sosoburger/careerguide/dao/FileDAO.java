package com.sosoburger.careerguide.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDAO {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "type")
    private String type;

    @Column(name = "data")
    @Lob
    private byte[] data;
}
