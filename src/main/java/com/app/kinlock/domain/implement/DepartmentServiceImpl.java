package com.app.kinlock.domain.implement;

import com.app.kinlock.data.DepartmentRepository;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.entities.Department;
import com.app.kinlock.domain.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl extends CRUDServiceImpl<Department, Integer> implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    protected GenericRepository<Department, Integer> getRepository() {
        return departmentRepository;
    }
}
