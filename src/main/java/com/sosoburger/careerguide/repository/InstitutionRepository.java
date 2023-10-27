package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.InstitutionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionDAO, Integer> {
}
