package com.app.kinlock.domain.implement;

import com.app.kinlock.data.DepartmentRepository;
import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.domain.entities.Department;
import com.app.kinlock.domain.service.DepartmentService;
import com.app.kinlock.presentation.dto.DepartmentDto;
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

    @Override
    public Department create(DepartmentDto dto) {
        Department department = new Department();
        department.setName(dto.getName());
        return this.create(department);
    }
}
