package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.FileDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDAO, Integer> {
}
