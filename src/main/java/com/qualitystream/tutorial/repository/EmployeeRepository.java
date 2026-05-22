package com.qualitystream.tutorial.repository;

import com.qualitystream.tutorial.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    // JpaRepository<MyEntityName, "ID 'integer' or 'string'?">
    // JpaRepository give us all the basic CRUD methods, like:
    // save(), findById(), findAll(), deleteById()

    Optional<EmployeeEntity> findByEmail(String email);
}
