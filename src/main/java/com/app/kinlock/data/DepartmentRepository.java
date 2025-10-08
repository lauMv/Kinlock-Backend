package com.app.kinlock.data;

import com.app.kinlock.domain.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends GenericRepository<Department, Integer> {
}
