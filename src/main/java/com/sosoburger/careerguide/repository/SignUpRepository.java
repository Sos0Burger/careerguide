package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.SignUpDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignUpRepository extends JpaRepository<SignUpDAO, Integer> {
}
