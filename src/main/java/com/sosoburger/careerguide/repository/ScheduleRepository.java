package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.ScheduleDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleDAO, Integer> {
}
