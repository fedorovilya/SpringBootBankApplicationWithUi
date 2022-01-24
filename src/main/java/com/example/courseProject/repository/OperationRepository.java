package com.example.courseProject.repository;

import com.example.courseProject.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByCategory(String category);
    Optional<Operation> findByOperationName(String operationName);
}
