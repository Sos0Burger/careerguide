package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.CompanyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDAO, Integer> {
}
