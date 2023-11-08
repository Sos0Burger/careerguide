package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.SignUpDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignUpRepository extends JpaRepository<SignUpDAO, Integer> {
    @Query("SELECT s FROM SignUpDAO s WHERE s.status = :status and s.schedule.company.id = :company")
    List<SignUpDAO> getReviewedSignUps(@Param("company")Integer companyId, @Param("status") Boolean status);

    @Query("SELECT s FROM SignUpDAO s WHERE s.status = null and s.schedule.company.id = :company")
    List<SignUpDAO> getPendingSignUps(@Param("company")Integer companyId);
    
}
