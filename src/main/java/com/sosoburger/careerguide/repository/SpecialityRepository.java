package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.SpecialityDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<SpecialityDAO, Integer> {
    List<SpecialityDAO> findByTypeContainingIgnoreCase(String type);
}
