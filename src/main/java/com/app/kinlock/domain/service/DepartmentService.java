package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Department;
import com.app.kinlock.presentation.dto.DepartmentDto;

public interface DepartmentService extends CRUDService<Department, Integer> {

    Department create(DepartmentDto dto);
}
