package com.sosoburger.careerguide.repository;

import com.sosoburger.careerguide.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Integer> {
    Optional<UserDAO> findByEmail(String email);
    Boolean existsByEmail(String email);
}
