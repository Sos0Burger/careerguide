package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.SignUpDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<SignUpDAO, Integer> {
}
