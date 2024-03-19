package com.marinamooringmanagement.dao;

import com.marinamooringmanagement.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query("SELECT e from EmployeeEntity e WHERE e.email = ?1")
    Optional<EmployeeEntity> findByEmail(String email);
}

