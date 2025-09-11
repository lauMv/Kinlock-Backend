package com.app.kinlock.data;

import com.app.kinlock.domain.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends GenericRepository<Department, Integer> {
}
