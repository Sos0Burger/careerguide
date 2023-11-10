package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.SignUpDAO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignUpRepository extends JpaRepository<SignUpDAO, Integer> {
    @Transactional
    @Query("SELECT s FROM SignUpDAO s WHERE s.status = :status and s.schedule.company.id = :company ORDER BY s.date DESC")
    List<SignUpDAO> getCompanyReviewedSignUps(@Param("company")Integer companyId, @Param("status") Boolean status);
    @Transactional
    @Query("SELECT s FROM SignUpDAO s WHERE s.status = null and s.schedule.company.id = :company ORDER BY s.date DESC")
    List<SignUpDAO> getCompanyPendingSignUps(@Param("company")Integer companyId);

    @Transactional
    @Query("SELECT s FROM SignUpDAO s WHERE s.status = :status and s.institution.id = :institution ORDER BY s.date DESC")
    List<SignUpDAO> getInstitutionReviewedSignUps(@Param("institution")Integer institutionId, @Param("status") Boolean status);

    @Transactional
    @Query("SELECT s FROM SignUpDAO s WHERE s.status = null and s.institution.id = :institution ORDER BY s.date DESC")
    List<SignUpDAO> getInstitutionPendingSignUps(@Param("institution")Integer institutionId);
    
}
