package com.app.kinlock.domain.service;

import com.app.kinlock.domain.entity.Insurance;
import com.app.kinlock.presentation.dto.InsuranceDto;

public interface InsuranceService extends CRUDService<Insurance, Integer> {

    Insurance create(InsuranceDto dto);

    Insurance update(Integer id, InsuranceDto dto);
}
