package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.ScheduleDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleDAO, Integer> {

    @Query("SELECT s FROM ScheduleDAO s WHERE s.company.id = :id")
    List<ScheduleDAO> findByCompany(@Param("id")Integer id);
}
